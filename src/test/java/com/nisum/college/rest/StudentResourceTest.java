package com.nisum.college.rest;

import com.nisum.college.bean.bo.StudentBO;
import com.nisum.college.service.StudentService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
public class StudentResourceTest {

    @InjectMocks
    private StudentResource studentResource;

    @Mock
    private StudentService studentService;

    @Mock
    private KafkaTemplate<String, Object> kafkaTemplate;

    List<StudentBO> studentsBOList;

    StudentBO studentBO;

    @Before
    public void setup() throws Exception {
        studentBO = new StudentBO();
        studentBO.setId(new Long(8));
        studentBO.setBranch("cse");
        studentBO.setCourse("java");
        studentBO.setPhoneNumber("9853326443");

        studentsBOList = Arrays.asList(studentBO);
    }

    @Test
    public void testgetAllStudents() {
        Mockito.when(studentService.getStudents()).thenReturn(studentsBOList);
        Response response = studentResource.getAllStudents();
        Assert.assertNotNull(response);
        Assert.assertEquals(200, response.getStatus());
    }

    @Test
    public void testgetStudentsByCourse() {
        Mockito.when(studentService.getStudentsByBranch(Mockito.anyString())).thenReturn(studentsBOList);
        Response response = studentResource.getStudentsByCourse("cse");
        Assert.assertNotNull(response);
        Assert.assertEquals(200, response.getStatus());
    }

    @Test
    public void testgetStudentsByPhone() {
        Mockito.when(studentService.getStudentByPhone(Mockito.anyString())).thenReturn(studentBO);
        Response response = studentResource.getStudentsByPhone("9989343535");
        Assert.assertNotNull(response);
        Assert.assertEquals(200, response.getStatus());
    }

    @Test
    public void testgetStudent() {
        Mockito.when(studentService.getStudent(Mockito.anyLong())).thenReturn(studentBO);
        Response response = studentResource.getStudent(new Long(4));
        Assert.assertNotNull(response);
        Assert.assertEquals(200, response.getStatus());
    }

    @Test
    public void testupdateStudentDetails() {
        Mockito.when(studentService.updateStudentDetails(Mockito.any())).thenReturn(studentBO);
        Response response = studentResource.updateStudentDetails(studentBO);
        Assert.assertNotNull(response);
        Assert.assertEquals(200, response.getStatus());
    }

    @Test
    public void testremoveStudent() {
        Mockito.doNothing().when(studentService).removeStudentDetails(Mockito.anyLong());
        Response response = studentResource.removeStudent(new Long(4));
        Assert.assertNotNull(response);
        Assert.assertEquals(200, response.getStatus());
        Assert.assertEquals("Deleted Successfully", response.getEntity());
    }

    /*@Test
    public void testaddStudent() {
        Mockito.when(studentService.addStudent(Mockito.any())).thenReturn(studentBO);
        Mockito.doNothing().when(kafkaTemplate).send(Mockito.anyString(),Mockito.any());

        Response response = studentResource.addStudent(studentBO);
        Assert.assertNotNull(response);
        Assert.assertEquals(201, response.getStatus());
    }*/

}
