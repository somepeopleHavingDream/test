package org.yangxin.test.guava;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import java.util.Map;

/**
 * @author yangxin
 * 2023/2/28 10:34
 */
public class HashBasedTableTest {

    public static void main(String[] args) {
        test1();
    }

    private static void test1() {
        Table<String, String, String> employeeTable = HashBasedTable.create();

        employeeTable.put("IBM", "101", "Mahesh");
        employeeTable.put("IBM", "102", "Ramesh");
        employeeTable.put("IBM", "103", "Suresh");

        employeeTable.put("Microsoft", "111","Sohan");
        employeeTable.put("Microsoft", "112","Mohan");
        employeeTable.put("Microsoft", "113","Rohan");

        employeeTable.put("TCS", "121","Ram");
        employeeTable.put("TCS", "122","Shyam");
        employeeTable.put("TCS", "123","Sunil");

        Map<String, String> ibmEmployees = employeeTable.row("IBM");
        for (Map.Entry<String, String> entry : ibmEmployees.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }

        for (String employer : employeeTable.rowKeySet()) {
            System.out.println(employer);
        }

        Map<String, String> employerMap = employeeTable.column("102");
        for (Map.Entry<String, String> entry : employerMap.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
}
