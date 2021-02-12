$(document).ready(function() {
    var locations;
    $.ajax({
        url: "/locations/",
        data: {
            format: 'xml'
        },
        error: function () {
            alert("Could not find any locations!");
        },
        success: function (data) {
            locations = data.getElementsByTagName("item");
            generateLocationsTable(locations);
        }
    });
});

$(document).ready(function() {
    var departments;
    var tableName = ".departments_table_body";
    $.ajax({
        url: "/departments/",
        data: {
            format: 'xml'
        },
        error: function () {
            alert("Could not find any departments!");
        },
        success: function (data) {
            departments = data.getElementsByTagName("item");
            generateDepartmentsTable(departments, tableName);
        }
    });
});

$(document).ready(function() {
    var departments;
    var tableName = ".departmentsByLoc_table_body";
    $.ajax({
        url: "/departments/1",
        data: {
            format: 'xml'
        },
        error: function () {
            alert("Could not find any departments!");
        },
        success: function (data) {
            departments = data.getElementsByTagName("item");
            generateDepartmentsTable(departments, tableName);
        }
    });
});

$(document).ready(function() {
    var employees;
    var tableName = ".employee_table_body";
    $.ajax({
        url: "/employees/",
        data: {
            format: 'xml'
        },
        error: function () {
            alert("Could not find any employees!");
        },
        success: function (data) {
            employees = data.getElementsByTagName("item");
            generateEmployeesTable(employees, tableName);
        }
    });
});

$(document).ready(function() {
    var employees;
    var tableName = ".employeesByDep_table_body";
    $.ajax({
        url: "/employees/1",
        data: {
            format: 'xml'
        },
        error: function () {
            alert("Could not find any employees!");
        },
        success: function (data) {
            employees = data.getElementsByTagName("item");
            generateEmployeesTable(employees, tableName);
        }
    });
});

$(document).ready(function() {
    var employees;
    var tableName = ".employeesByName_table_body";
    $.ajax({
        url: "/employees/byName?employeeName=kor",
        data: {
            format: 'xml'
        },
        error: function () {
            alert("Could not find any employees!");
        },
        success: function (data) {
            employees = data.getElementsByTagName("item");
            generateEmployeesTable(employees, tableName);
        }
    });
});

$(document).ready(function() {
    var employee;
    $.ajax({
        url: "/employee/2",
        data: {
            format: 'xml'
        },
        error: function () {
            alert("Could not find an employee!");
        },
        success: function (data) {
            employee = data.getElementsByTagName('Employee')[0];
            generateEmployeeTable(employee);
        }
    });
});

function generateLocationsTable(locations) {
    var tableBody = $('.location_table_body')
    tableBody.empty();
    for(i = 0; i < locations.length; i++) {
        tableBody.append(
            "<tr>" +
                "<th>" + locations[i].childNodes[0].innerHTML + "</th>" +
                "<td>" + locations[i].childNodes[1].innerHTML + "</td>" +
            "</tr>"
        )
    }
}

function generateDepartmentsTable(departments, tableName) {
    var tableBody = $(tableName)
    tableBody.empty();
    for(i = 0; i < departments.length; i++) {
        tableBody.append(
            "<tr>" +
                "<th>" + departments[i].childNodes[0].innerHTML + "</th>" +
                "<td>" + departments[i].childNodes[1].innerHTML + "</td>" +
                "<td>" + departments[i].childNodes[2].childNodes[1].innerHTML + "</td>" +
            "</tr>"
        )
    }
}

function generateEmployeesTable(employees, tableName) {
    console.log(employees);
    console.log(tableName);
    var tableBody = $(tableName)
    tableBody.empty();
    for(i = 0; i < employees.length; i++) {
        tableBody.append(
            "<tr>" +
                "<th>" + employees[i].childNodes[0].innerHTML + "</th>" +
                "<td>" + employees[i].childNodes[1].innerHTML + "</td>" +
                "<td>" + employees[i].childNodes[2].innerHTML + "</td>" +
                "<td>" + employees[i].childNodes[3].innerHTML + "</td>" +
            "</tr>"
        )
    }
}

function generateEmployeeTable(employee) {
    console.log(employee);
    var tableBody = $('.employeeInfo_table_body')
    tableBody.empty();
    tableBody.append(
            "<tr>" +
                "<th>" + employee.childNodes[0].innerHTML + "</th>" +
                "<td>" + employee.childNodes[1].innerHTML + "</td>" +
                "<td>" + employee.childNodes[2].innerHTML + "</td>" +
                "<td>" + employee.childNodes[3].innerHTML + "</td>" +
                "<td>" + employee.childNodes[4].childNodes[1].innerHTML + "</td>" +
                "<td>" + employee.childNodes[5].innerHTML + "</td>" +
                "<td>" + employee.childNodes[6].innerHTML + "</td>" +
                "<td>" + employee.childNodes[7].innerHTML + "</td>" +
                "<td>" + employee.childNodes[8].childNodes[1].innerHTML + "</td>" +
            "</tr>"
        )
    
}