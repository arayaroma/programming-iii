-- 1. Biggest Actual Salary by Region
SELECT r.REGION_ID, r.REGION_NAME, MAX(e.SALARY) AS "Biggest Actual Salary"
FROM Regions r
JOIN Countries c ON r.REGION_ID = c.REGION_ID
JOIN Locations l ON c.COUNTRY_ID = l.COUNTRY_ID
JOIN Departments d ON l.LOCATION_ID = d.LOCATION_ID
JOIN Employees e ON d.DEPARTMENT_ID = e.DEPARTMENT_ID
GROUP BY r.REGION_ID, r.REGION_NAME
ORDER BY r.REGION_ID DESC;

-- 2. Personal Quantity, Minimum Salary, Maximum Salary, Manager
SELECT e.EMPLOYEE_ID AS MANAGER_ID, e.FIRST_NAME, e.LAST_NAME, COUNT(e.EMPLOYEE_ID) AS "Personal Quantity", MIN(d.SALARY) AS "Minimum Salary", MAX(d.SALARY) AS "Maximum Salary"
FROM Employees e
JOIN Employees d ON e.EMPLOYEE_ID = d.MANAGER_ID
GROUP BY e.EMPLOYEE_ID, e.FIRST_NAME, e.LAST_NAME
ORDER BY e.EMPLOYEE_ID ASC;

-- 3. Job Information, Where Sum(Salary > 40,000)
SELECT j.JOB_ID AS JOB_ID, j.JOB_TITLE AS JOB, SUM(e.SALARY) AS "Salaries"
FROM Jobs j
JOIN Employees e ON j.JOB_ID = e.JOB_ID
GROUP BY j.JOB_ID, j.JOB_TITLE
HAVING SUM(e.SALARY) > 40000
ORDER BY j.JOB_ID ASC;

-- 4. Employee(s) with max quantity of Job change
SELECT e.EMPLOYEE_ID, e.FIRST_NAME, e.LAST_NAME, COUNT(e.EMPLOYEE_ID) AS "Job Change"
FROM Employees e
JOIN Job_history j ON e.EMPLOYEE_ID = j.EMPLOYEE_ID
GROUP BY e.EMPLOYEE_ID, e.FIRST_NAME, e.LAST_NAME
HAVING (COUNT(e.EMPLOYEE_ID) = (
    SELECT MAX(COUNT(e.EMPLOYEE_ID)) 
    FROM Employees e 
    JOIN Job_history j ON e.EMPLOYEE_ID = j.EMPLOYEE_ID 
    GROUP BY e.EMPLOYEE_ID))
ORDER BY e.EMPLOYEE_ID ASC;