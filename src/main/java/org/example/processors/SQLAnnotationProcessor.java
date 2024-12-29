package org.example.processors;

import org.example.annotations.Column;
import org.example.annotations.Id;
import org.example.annotations.Table;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import javax.lang.model.type.TypeMirror;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.Writer;
import java.util.Set;

@SupportedAnnotationTypes({"org.example.annotations.Table"})
@SupportedSourceVersion(SourceVersion.RELEASE_17)
public class SQLAnnotationProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (Element element : roundEnv.getElementsAnnotatedWith(Table.class)) {
            if (element.getKind() == ElementKind.CLASS) {
                processTable((TypeElement) element);
            }
        }
        return true;
    }

    private void processTable(TypeElement classElement) {
        Table tableAnnotation = classElement.getAnnotation(Table.class);
        String tableName = tableAnnotation.name();

        StringBuilder createQuery = new StringBuilder("CREATE TABLE ").append(tableName).append(" (");
        StringBuilder fieldsMapping = new StringBuilder();
        StringBuilder insertColumns = new StringBuilder();
        StringBuilder insertValues = new StringBuilder();
        StringBuilder updateSet = new StringBuilder();

        String idColumn = null;

        for (Element field : classElement.getEnclosedElements()) {
            if (field.getKind() == ElementKind.FIELD && field.getAnnotation(Column.class) != null) {
                Column columnAnnotation = field.getAnnotation(Column.class);
                String columnName = columnAnnotation.name();
                String columnType = getSQLType(field.asType());

                if (field.getAnnotation(Id.class) != null) {
                    createQuery.append(columnName).append(" ").append(columnType).append(" PRIMARY KEY, ");
                    idColumn = columnName;
                } else {
                    createQuery.append(columnName).append(" ").append(columnType).append(", ");
                }

                insertColumns.append(columnName).append(", ");
                insertValues.append("?, ");
                updateSet.append(columnName).append(" = ?, ");

                fieldsMapping.append("columns.put(\"").append(columnName).append("\", \"")
                        .append(columnType).append("\");\n");
            }
        }

        createQuery.setLength(createQuery.length() - 2);
        createQuery.append(");");

        insertColumns.setLength(insertColumns.length() - 2);
        insertValues.setLength(insertValues.length() - 2);
        updateSet.setLength(updateSet.length() - 2);

        String insertQuery = "INSERT INTO " + tableName + " (" + insertColumns + ") VALUES (" + insertValues + ");";
        String updateQuery = "UPDATE " + tableName + " SET " + updateSet + " WHERE " + idColumn + " = ?;";
        String deleteQuery = "DELETE FROM " + tableName + " WHERE " + idColumn + " = ?;";

        generateSQLGeneratorClass(classElement, tableName, createQuery.toString(), insertQuery, updateQuery, deleteQuery, fieldsMapping.toString());
    }

    private String getSQLType(TypeMirror typeMirror) {
        switch (typeMirror.toString()) {
            case "int":
            case "java.lang.Integer":
                return "INT";
            case "java.lang.String":
                return "VARCHAR(255)";
            case "double":
            case "java.lang.Double":
                return "DOUBLE";
            default:
                return "TEXT";
        }
    }

    private void generateSQLGeneratorClass(TypeElement classElement, String tableName, String createQuery, String insertQuery, String updateQuery, String deleteQuery, String fieldsMapping) {
        String packageName = processingEnv.getElementUtils().getPackageOf(classElement).getQualifiedName().toString();
        String className = classElement.getSimpleName() + "SQLGenerator";

        StringBuilder insertParams = new StringBuilder();
        StringBuilder insertValues = new StringBuilder();
        StringBuilder updateSet = new StringBuilder();
        StringBuilder selectColumns = new StringBuilder();
        String idField = null;

        for (Element field : classElement.getEnclosedElements()) {
            if (field.getKind() == ElementKind.FIELD && field.getAnnotation(Column.class) != null) {
                String fieldName = field.getSimpleName().toString();
                String getter = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);

                if (field.getAnnotation(Id.class) != null) {
                    idField = fieldName; // Збереження ідентифікатора
                } else {
                    updateSet.append(fieldName).append(" = '\" + obj.").append(getter).append("() + \"', ");
                }

                insertParams.append(fieldName).append(", ");
                insertValues.append("'\" + obj.").append(getter).append("() + \"', ");
                selectColumns.append(fieldName).append(", ");
            }
        }

        if (idField == null) {
            processingEnv.getMessager().printMessage(Diagnostic.Kind.WARNING,
                    "Class " + classElement.getSimpleName() + " does not have a field annotated with @Id.");
            return;
        }

        if (insertParams.length() > 0) {
            insertParams.setLength(insertParams.length() - 2); // Видалити останню ", "
            insertValues.setLength(insertValues.length() - 2); // Видалити останню ", "
            selectColumns.setLength(selectColumns.length() - 2); // Видалити останню ", "
        }

        if (updateSet.length() > 0) {
            updateSet.setLength(updateSet.length() - 2); // Видалити останню ", "
        }

        String selectQuery = "SELECT " + selectColumns + " FROM " + tableName + ";";

        try {
            JavaFileObject fileObject = processingEnv.getFiler().createSourceFile(packageName + "." + className);
            try (Writer writer = fileObject.openWriter()) {
                writer.write("package " + packageName + ";\n\n");
                writer.write("import " + classElement.getQualifiedName() + ";\n");
                writer.write("import java.util.HashMap;\n");
                writer.write("import java.util.Map;\n\n");
                writer.write("public class " + className + " {\n\n");

                // Поля класу для SQL-запитів
                writer.write("    public static final String CREATE_QUERY = \"" + createQuery + "\";\n\n");
                writer.write("    public static final String INSERT_QUERY = \"" + insertQuery + "\";\n\n");
                writer.write("    public static final String UPDATE_QUERY = \"" + updateQuery + "\";\n\n");
                writer.write("    public static final String DELETE_QUERY = \"" + deleteQuery + "\";\n\n");
                writer.write("    public static final String SELECT_QUERY = \"" + selectQuery + "\";\n\n");

                // Метод для генерації INSERT-запиту
                writer.write("    public static String generateInsertQuery(" + classElement.getSimpleName() + " obj) {\n");
                writer.write("        return \"INSERT INTO " + tableName + " (" + insertParams + ") VALUES (" + insertValues + ");\";\n");
                writer.write("    }\n\n");

                // Метод для генерації UPDATE-запиту
                writer.write("    public static String generateUpdateQuery(" + classElement.getSimpleName() + " obj) {\n");
                writer.write("        return \"UPDATE " + tableName + " SET " + updateSet + " WHERE " + idField + " = '\" + obj.get" + idField.substring(0, 1).toUpperCase() + idField.substring(1) + "() + \"';\";\n");
                writer.write("    }\n\n");

                // Метод для генерації DELETE-запиту
                writer.write("    public static String generateDeleteQuery(int id) {\n");
                writer.write("        return \"DELETE FROM " + tableName + " WHERE " + idField + " = \" + id + \";\";\n");
                writer.write("    }\n\n");

                // Метод для отримання мапи колонок
                writer.write("    public static Map<String, String> getColumns() {\n");
                writer.write("        Map<String, String> columns = new HashMap<>();\n");
                writer.write(fieldsMapping);
                writer.write("        return columns;\n");
                writer.write("    }\n\n");

                writer.write("}\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
