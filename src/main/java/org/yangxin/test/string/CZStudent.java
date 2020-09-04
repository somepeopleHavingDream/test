package org.yangxin.test.string;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 常州开放服务门户接口返回的学生信息
 *
 * @author yangxin
 * 2020/09/04 10:39
 */
public class CZStudent {

    /**
     * 学校名称
     */
    @JsonProperty("SCHOOL_NAME")
    private String schoolName;

    /**
     * 学生性别
     */
    @JsonProperty("SEX")
    private String sex;

    /**
     * 班级编号（注意：这个地方开放服务门户接口返回过来的字段名称为class，但java中有命名关键字约束，故需要对此做一定的处理）
     */
    @JsonProperty("CLASS")
//    private Integer classNo;
    private String classNo;

    /**
     * 学校Id
     */
    @JsonProperty("SCHOOL_ID")
    private String schoolId;

    /**
     * 学生姓名
     */
    @JsonProperty("NAME")
    private String name;

    /**
     * 更新时间
     */
    @JsonProperty("XGSJ")
//    private Date updateTime;
    private String updateTime;

    /**
     * 所属区域
     */
    @JsonProperty("AREA")
    private String area;

    /**
     * 入学时间
     */
    @JsonProperty("ENROLLMENTYEAR")
    private String enrollmentYear;

    /**
     * 全国学籍号
     */
    @JsonProperty("QGXJH")
//    private String nationalStudentRegistrationNumber;
    private String nationalStudentRegistrationNumber;

    /**
     * 年级
     */
    @JsonProperty("GRADE")
    private String grade;

    /**
     * Id
     */
    @JsonProperty("ID")
    private String id;

    /**
     * 学校类型
     */
    @JsonProperty("SCHOOL_TYPE")
    private String schoolType;

    /**
     * 学籍辅号
     */
    @JsonProperty("XJFH")
//    private Integer secondaryNumber;
    private String secondaryNumber;

    /**
     * 实际年级
     */
    @JsonProperty("ACTUALGRADE")
    private String actualGrade;

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getClassNo() {
        return classNo;
    }

    public void setClassNo(String classNo) {
        this.classNo = classNo;
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getEnrollmentYear() {
        return enrollmentYear;
    }

    public void setEnrollmentYear(String enrollmentYear) {
        this.enrollmentYear = enrollmentYear;
    }

    public String getNationalStudentRegistrationNumber() {
        return nationalStudentRegistrationNumber;
    }

    public void setNationalStudentRegistrationNumber(String nationalStudentRegistrationNumber) {
        this.nationalStudentRegistrationNumber = nationalStudentRegistrationNumber;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSchoolType() {
        return schoolType;
    }

    public void setSchoolType(String schoolType) {
        this.schoolType = schoolType;
    }

    public String getSecondaryNumber() {
        return secondaryNumber;
    }

    public void setSecondaryNumber(String secondaryNumber) {
        this.secondaryNumber = secondaryNumber;
    }

    public String getActualGrade() {
        return actualGrade;
    }

    public void setActualGrade(String actualGrade) {
        this.actualGrade = actualGrade;
    }

    @Override
    public String toString() {
        return "CZStudent{" +
                "schoolName='" + schoolName + '\'' +
                ", sex='" + sex + '\'' +
                ", classNo='" + classNo + '\'' +
                ", schoolId='" + schoolId + '\'' +
                ", name='" + name + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", area='" + area + '\'' +
                ", enrollmentYear='" + enrollmentYear + '\'' +
                ", nationalStudentRegistrationNumber='" + nationalStudentRegistrationNumber + '\'' +
                ", grade='" + grade + '\'' +
                ", id='" + id + '\'' +
                ", schoolType='" + schoolType + '\'' +
                ", secondaryNumber='" + secondaryNumber + '\'' +
                ", actualGrade='" + actualGrade + '\'' +
                '}';
    }
}
