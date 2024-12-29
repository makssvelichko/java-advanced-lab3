package org.example.examples;

import org.example.annotations.Column;
import org.example.annotations.Id;
import org.example.annotations.Table;

@Table(name = "Message")
public class Message {
  @Id
  @Column(name = "id")
  private int id;

  @Column(name = "text")
  private String text;

  @Column(name = "sender")
  private String sender;

  @Column(name = "receiver")
  private String receiver;

  // Конструктор
  public Message(String text, String sender, String receiver) {
    this.text = text;
    this.sender = sender;
    this.receiver = receiver;
  }

  // Геттери та сеттери
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public String getSender() {
    return sender;
  }

  public void setSender(String sender) {
    this.sender = sender;
  }

  public String getReceiver() {
    return receiver;
  }

  public void setReceiver(String receiver) {
    this.receiver = receiver;
  }
}
