/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package problem4;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 *
 * @author eck
 */
class Employee {

    public static enum Gender {

        MALE, FEMALE
    }

    private String name;
    private Gender gender;
    private LocalDate dob;
    private double income;
    private UUID employeeID;

    private Employee(String name, Gender gender, LocalDate dob,
            double income, UUID employeeID) {
        
        this.name = name;
        this.gender = gender;
        this.dob = dob;
        this.income = income;
        this.employeeID = employeeID;
    }

    public Employee() {
    }

    public double getIncome() {
        return income;
    }

    public Gender getGender() {
        return gender;
    }

    public static List<Employee> persons() {
        Employee p1 = new Employee( "Jake", Gender.MALE, LocalDate.of(1971,
                Month.JANUARY, 1), 2343.0, UUID.randomUUID());
        Employee p2 = new Employee("Jack", Gender.MALE, LocalDate.of(1972,
                Month.JULY, 21), 7100.0, UUID.randomUUID());
        Employee p3 = new Employee( "Jane", Gender.FEMALE, LocalDate.of(1973,
                Month.MAY, 29), 5455.0, UUID.randomUUID());
        Employee p4 = new Employee("Jode", Gender.MALE, LocalDate.of(1974,
                Month.OCTOBER, 16), 1800.0, UUID.randomUUID());
        Employee p5 = new Employee( "Jeny", Gender.FEMALE, LocalDate.of(1975,
                Month.DECEMBER, 13), 1234.0, UUID.randomUUID());
        Employee p6 = new Employee( "Jason", Gender.MALE, LocalDate.of(1976,
                Month.JUNE, 9), 3211.0, UUID.randomUUID());

        List<Employee> persons = Arrays.asList(p1, p2, p3, p4, p5, p6);

        return persons;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Double.compare(employee.getIncome(), getIncome()) == 0 && Objects.equals(name, employee.name) && gender == employee.gender && Objects.equals(dob, employee.dob) && Objects.equals(employeeID, employee.employeeID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, gender, dob, getIncome(), employeeID);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", gender=" + gender +
                ", dob=" + dob +
                ", income=" + income +
                ", employeeID=" + employeeID +
                '}';
    }

    public static void statistics() {
        List<Employee> list = persons();

        double sumSalary = list
                .stream()
                .mapToDouble(Employee::getIncome)
                .sum();

        System.out.printf("The sum of the employees' income is: %.2f%n", sumSalary);
    }

    public void personsStatsByGenderCount() {
        List<Employee> list = persons();

        Map<Gender, Long> employeeCountByGender =
                list.stream()
                        .collect(Collectors.groupingBy(Employee::getGender, Collectors.counting()));

        employeeCountByGender.forEach(
                (gender, count) -> System.out.printf("There are %d %s(s)%n", count, gender.toString()));
    }

    public void personsStatsByGenderList() {
        List<Employee> list = persons();

        Map<Gender, List<Employee>> groupedByGender =
                list.stream()
                        .collect(Collectors.groupingBy(Employee::getGender));

        groupedByGender.forEach(
                (gender, employeesOfGender) ->
                {
                    System.out.println(gender);
                    employeesOfGender.forEach(
                            employee -> System.out.printf("  %s%n", employee));
                }
        );
    }


}

