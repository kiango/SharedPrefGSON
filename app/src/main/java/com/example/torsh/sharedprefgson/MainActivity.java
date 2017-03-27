package com.example.torsh.sharedprefgson;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by torsh on 3/21/17.
 */

public class MainActivity extends Activity {


    private final String TAG = MainActivity.class.getSimpleName();
    private TextView txvDisplay;

    private List<Employee> employeeList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txvDisplay = (TextView) findViewById(R.id.textViewDisp);
    }


    private Employee getEmployeeObject(){
        Employee employee = new Employee();
        employee.setName("joe");
        employee.setProfession("devo");
        employee.setProfId(398);
        employee.setRoles(Arrays.asList("volu","tester","developer"));

        return employee;
    }

    public void saveObjectType(View view) {

        Employee employee = getEmployeeObject();

        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // serialization by Gson library added in Gradle build file
        Gson gson = new Gson();
        String jsonString = gson.toJson(employee, Employee.class);
        Log.i(TAG + " saved ", jsonString);

        editor.putString("employee_key", jsonString);
        editor.apply();
    }

    public void loadObjectType(View view) {

        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        String jsonString = sharedPreferences.getString("employee_key", "N/A");
        Log.i(TAG + "loaded ", jsonString);

        // deserialization of Gson obj
        Gson gson = new Gson();
        //convert json string to Employee type and save it to employee
        Employee employee = gson.fromJson(jsonString, Employee.class);

        displayText(employee);
    }

    private void displayText(Employee employee) {
        // if employee is not set
        if (employee == null)
            return;

        String displayTxt = employee.getName()
                + "\n" + employee.getProfession()
                + "\n" + employee.getProfId()
                + "\n" + employee.getRoles().toString();
        txvDisplay.setText(displayTxt);
    }

    public void loadGenericType(View view) {

        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        String jsonString = sharedPreferences.getString("foo_key", "N/A");
        Log.i(TAG + "loaded foo ", jsonString);


        // deserialization of Gson obj
        Gson gson = new Gson();
        // use Typetoken class to get the type of our generic type
        Type type = new TypeToken<Foo<Employee>>() {}.getType();
        //convert json string to Employee type and save it to employee
        // gson.fromJson returns Foo<Employee>
        Foo<Employee> employeeFoo = gson.fromJson(jsonString, type);
        Employee employee = employeeFoo.getObject();

        displayText(employee);

    }

    public void saveGenericType(View view) {

        Employee employee = getEmployeeObject();
        Foo<Employee> foo = new Foo<>();
        foo.setObject(employee);

        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // serialization by Gson library added in Gradle build file
        Gson gson = new Gson();
        // this is for passing a type object into the gson.toJson
        // toJson does not accept generics!!
        Type type = new TypeToken<Foo<Employee>>(){}.getType();
        String jsonString = gson.toJson(foo, type);
        Log.i(TAG + " saved obj ", jsonString);

        editor.putString("foo_key", jsonString);
        editor.apply();

    }

    // adds new employee object into the ArrayList of objects, per click
    public void saveEmployeeList(View view) {
        employeeList.add(getEmployeeObject());
        //List<Employee> emp = getAllEmployeesObj();
        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        // serialization by Gson library added in Gradle build file
        Gson gson = new Gson();
        // this is for passing a type object into the gson.toJson
        // toJson does not accept generics!!
        Type type = new TypeToken<List<Employee>>(){}.getType();
        String jsonString = gson.toJson(employeeList, type);
        Log.i(TAG + " saved obj list ", jsonString);

        editor.putString("foo_key", jsonString);
        editor.apply();

    }

    //ToDo: load all employee objects from the employeeList
    public void loadEmployeeList(View view) {
    }
}
