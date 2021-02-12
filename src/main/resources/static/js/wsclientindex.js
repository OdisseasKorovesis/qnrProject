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
            generateDepartmentsTable(departments);
        }
    });
});

$(document).ready(function() {
    var employees;
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
            generateEmployeesTable(employees);
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

function generateDepartmentsTable(departments) {
    var tableBody = $('.department_table_body')
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

function generateEmployeesTable(employees) {
    var tableBody = $('.employee_table_body')
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