package org.yangxin.test.classinjdk.serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * @author yangxin
 * 2019/12/31 17:54
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
//public class Student {
public class Student implements Serializable {

    private static final long serialVersionUID = -363987537630696589L;

    private String username;
    private Integer age;

    public static void main(String[] args) throws IOException {
        Student alice = new Student("Alice", 13);
        Student bob = new Student("Bob", 15);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("stu.dat"));
        objectOutputStream.writeObject(alice);
        objectOutputStream.writeObject(bob);
        objectOutputStream.close();
    }
}
