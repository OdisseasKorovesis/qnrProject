INSERT INTO locations (name) VALUES ('Athens');
INSERT INTO locations (name) VALUES ('Thessaloniki');
INSERT INTO departments (name, location_id) VALUES ('Sales', '1');
INSERT INTO departments (name, location_id) VALUES ('IT', '2');
INSERT INTO departments (name, location_id) VALUES ('HR', '1');
INSERT INTO employees (commissions, first_name, hire_date, job, last_name, salary, department_id) VALUES ('200', 'Katerina', '2020/10/08', 'Sales Assistant', 'Papanoutsou', '1200', '1');
INSERT INTO employees (commissions, first_name, hire_date, job, last_name, salary, department_id, manager_id) VALUES ('200', 'Odisseas', '2020/11/15', 'Developer', 'Korovesis', '100', '2', '1');
