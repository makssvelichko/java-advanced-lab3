/**
 * Represents a message entity with attributes such as text, sender, and receiver.
 * Annotated to map to a database table named "Message" with corresponding columns.
 */
package org.example.examples;

import org.example.annotations.Column;
import org.example.annotations.Id;
import org.example.annotations.Table;

/**
 * Maps the Message class to the "Message" table in the database.
 */
@Table(name = "Message")
public class Message {

  /**
   * Unique identifier for the message, mapped to the "id" column in the database.
   */
  @Id
  @Column(name = "id")
  private int id;

  /**
   * The text content of the message, mapped to the "text" column in the database.
   */
  @Column(name = "text")
  private String text;

  /**
   * The sender of the message, mapped to the "sender" column in the database.
   */
  @Column(name = "sender")
  private String sender;

  /**
   * The receiver of the message, mapped to the "receiver" column in the database.
   */
  @Column(name = "receiver")
  private String receiver;

  /**
   * Constructs a new Message instance.
   *
   * @param text     the content of the message
   * @param sender   the sender of the message
   * @param receiver the receiver of the message
   */
  public Message(String text, String sender, String receiver) {
    this.text = text;
    this.sender = sender;
    this.receiver = receiver;
  }

  /**
   * Gets the unique identifier of the message.
   *
   * @return the message's ID
   */
  public int getId() {
    return id;
  }

  /**
   * Sets the unique identifier of the message.
   *
   * @param id the message's ID
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * Gets the text content of the message.
   *
   * @return the message's text
   */
  public String getText() {
    return text;
  }

  /**
   * Sets the text content of the message.
   *
   * @param text the message's text
   */
  public void setText(String text) {
    this.text = text;
  }

  /**
   * Gets the sender of the message.
   *
   * @return the sender's name
   */
  public String getSender() {
    return sender;
  }

  /**
   * Sets the sender of the message.
   *
   * @param sender the sender's name
   */
  public void setSender(String sender) {
    this.sender = sender;
  }

  /**
   * Gets the receiver of the message.
   *
   * @return the receiver's name
   */
  public String getReceiver() {
    return receiver;
  }

  /**
   * Sets the receiver of the message.
   *
   * @param receiver the receiver's name
   */
  public void setReceiver(String receiver) {
    this.receiver = receiver;
  }
}
