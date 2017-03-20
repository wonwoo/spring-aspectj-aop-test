package me.wonwoo.repository;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by wonwoo on 2017. 3. 20..
 */
@Entity
public class Hello {
  @Id
  @GeneratedValue
  private Long id;

  private String name;

  Hello() {

  }

  public Hello(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "Hello{" +
      "id=" + id +
      ", name='" + name + '\'' +
      '}';
  }
}
