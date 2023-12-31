-- SELECT EMPLEADOS & SALARIOS
SELECT * 
FROM PLA_HISSALARIOS ph, PLA_EMPLEADOS pe  
WHERE ph.SAL_EMPID = pe.ID;

-- SELECT EMPLEADOS & SALARIOS CONTAINING 'ar'
SELECT *
FROM PLA_HISSALARIOS ph 
JOIN PLA_EMPLEADOS pe 
ON ph.SAL_EMPID = pe.ID 
WHERE pe.EMP_NOMBRE LIKE '%ar%';