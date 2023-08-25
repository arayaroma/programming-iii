/*
Created: 19/7/2017
Modified: 18/5/2020
Model: UnaPlanilla
Database: Oracle 11g Release 2
*/

-- Create sequences section -------------------------------------------------

CREATE SEQUENCE PLAM_TIPOPLANILLAS_SEQ01
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 NOMINVALUE
 NOCACHE;

CREATE SEQUENCE PLAM_EMPLEADOS_SEQ01
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 NOMINVALUE
 NOCACHE;

-- Create tables section -------------------------------------------------

-- Table plam_empleados

CREATE TABLE PLAM_EMPLEADOS(
  EMP_ID NUMBER NOT NULL,
  EMP_NOMBRE VARCHAR2(30 ) NOT NULL,
  EMP_PAPELLIDO VARCHAR2(15 ) NOT NULL,
  EMP_SAPELLIDO VARCHAR2(15 ) NOT NULL,
  EMP_CEDULA VARCHAR2(40 ) NOT NULL,
  EMP_GENERO VARCHAR2(1 ) DEFAULT 'M' NOT NULL CONSTRAINT PLAM_EMPLEADOS_CK03 CHECK (EMP_GENERO IN ('M', 'F')),
  EMP_CORREO VARCHAR2(80 ),
  EMP_ADMINISTRADOR VARCHAR2(1 ) DEFAULT 'N' NOT NULL CONSTRAINT PLAM_EMPLEADOS_CK02 CHECK (EMP_ADMINISTRADOR IN ('S', 'N')),
  EMP_USUARIO VARCHAR2(15 ),
  EMP_CLAVE VARCHAR2(8 ),
  EMP_FINGRESO DATE NOT NULL,
  EMP_FSALIDA DATE,
  EMP_ESTADO VARCHAR2(1 ) DEFAULT 'A' NOT NULL CONSTRAINT PLAM_EMPLEADOS_CK01 CHECK (EMP_ESTADO IN ('A', 'I')),
  EMP_VERSION NUMBER DEFAULT 1 NOT NULL
);

-- Create indexes for table plam_empleados

CREATE UNIQUE INDEX PLAM_EMPLEADOS_IND01 ON PLAM_EMPLEADOS (EMP_USUARIO);

-- Add keys for table plam_empleados

ALTER TABLE PLAM_EMPLEADOS ADD CONSTRAINT PLAM_EMPLEADOS_PK PRIMARY KEY (EMP_ID);

-- Table and Columns comments section

COMMENT ON TABLE PLAM_EMPLEADOS IS 'Tabla de empleados';
COMMENT ON COLUMN PLAM_EMPLEADOS.EMP_ID IS 'Id del empleado';
COMMENT ON COLUMN PLAM_EMPLEADOS.EMP_NOMBRE IS 'Nombre del empleado';
COMMENT ON COLUMN PLAM_EMPLEADOS.EMP_PAPELLIDO IS 'Primer apellido del empleado';
COMMENT ON COLUMN PLAM_EMPLEADOS.EMP_SAPELLIDO IS 'Segundo apellido del empleado';
COMMENT ON COLUMN PLAM_EMPLEADOS.EMP_CEDULA IS 'Cedula del empleado';
COMMENT ON COLUMN PLAM_EMPLEADOS.EMP_GENERO IS 'Genero del empleado (M:Masculino, F:Femenino)';
COMMENT ON COLUMN PLAM_EMPLEADOS.EMP_CORREO IS 'Correo electronico del empleado';
COMMENT ON COLUMN PLAM_EMPLEADOS.EMP_ADMINISTRADOR IS 'Empleado administador del sistema (S:Si, N:No)';
COMMENT ON COLUMN PLAM_EMPLEADOS.EMP_USUARIO IS 'Usuario del sistema para el empleado';
COMMENT ON COLUMN PLAM_EMPLEADOS.EMP_CLAVE IS 'Clave del sistema para el empleado';
COMMENT ON COLUMN PLAM_EMPLEADOS.EMP_FINGRESO IS 'Fecha de ingreso del empleado';
COMMENT ON COLUMN PLAM_EMPLEADOS.EMP_FSALIDA IS 'Fecha de salida del empleado';
COMMENT ON COLUMN PLAM_EMPLEADOS.EMP_ESTADO IS 'Estado del empleado (A:Activo, I:Inactivo)';
COMMENT ON COLUMN PLAM_EMPLEADOS.EMP_VERSION IS 'Version del registro';

-- Table plam_tipoplanillas

CREATE TABLE PLAM_TIPOPLANILLAS(
  TPLA_ID NUMBER NOT NULL,
  TPLA_CODIGO VARCHAR2(4 ) NOT NULL,
  TPLA_DESCRIPCION VARCHAR2(40 ) NOT NULL,
  TPLA_PLAXMES NUMBER NOT NULL,
  TPLA_ANOULTPLA NUMBER,
  TPLA_MESULTPLA NUMBER,
  TPLA_NUMULTPLA NUMBER,
  TPLA_ESTADO VARCHAR2(1 ) DEFAULT 'A' NOT NULL CONSTRAINT PLAM_TIPOPLANILLAS_CK01 CHECK (TPLA_ESTADO IN ('A', 'I')),
  TPLA_VERSION NUMBER DEFAULT 1 NOT NULL
);

-- Create indexes for table plam_tipoplanillas

CREATE UNIQUE INDEX PLAM_TIPOPLANILLAS_IND01 ON PLAM_TIPOPLANILLAS (TPLA_CODIGO);

-- Add keys for table plam_tipoplanillas

ALTER TABLE PLAM_TIPOPLANILLAS ADD CONSTRAINT PLAM_TIPOPLANILLAS_PK PRIMARY KEY (TPLA_ID);

-- Table and Columns comments section

COMMENT ON COLUMN PLAM_TIPOPLANILLAS.TPLA_ID IS 'Id del tipo de planilla';
COMMENT ON COLUMN PLAM_TIPOPLANILLAS.TPLA_CODIGO IS 'Codigo del tipo de planilla';
COMMENT ON COLUMN PLAM_TIPOPLANILLAS.TPLA_DESCRIPCION IS 'Descripcion o nombre de la planilla';
COMMENT ON COLUMN PLAM_TIPOPLANILLAS.TPLA_PLAXMES IS 'Cantidad de planillas a generar por mes';
COMMENT ON COLUMN PLAM_TIPOPLANILLAS.TPLA_ANOULTPLA IS 'Año de la ultima planilla generada';
COMMENT ON COLUMN PLAM_TIPOPLANILLAS.TPLA_MESULTPLA IS 'Mes de la ultima planilla generada';
COMMENT ON COLUMN PLAM_TIPOPLANILLAS.TPLA_NUMULTPLA IS 'Numero de la ultima planilla generada';
COMMENT ON COLUMN PLAM_TIPOPLANILLAS.TPLA_ESTADO IS 'Estado del tipo de planilla(A:Activa,I:Inactiva)';
COMMENT ON COLUMN PLAM_TIPOPLANILLAS.TPLA_VERSION IS 'Version del registro';

-- Table plam_empleadosplanilla

CREATE TABLE PLAM_EMPLEADOSPLANILLA(
  EXP_IDTPLA NUMBER NOT NULL,
  EXP_IDEMP NUMBER NOT NULL
);

-- Add keys for table plam_empleadosplanilla

ALTER TABLE PLAM_EMPLEADOSPLANILLA ADD CONSTRAINT PLAM_EMPLEADOSPLANILLA_PK PRIMARY KEY (EXP_IDTPLA, EXP_IDEMP);

-- Table and Columns comments section

COMMENT ON TABLE PLAM_EMPLEADOSPLANILLA IS 'Tabla ralacional de empleados por tipo de planilla';

-- Trigger for sequence plam_empleados_sec01 for column emp_id in table plam_empleados ---------
CREATE OR REPLACE TRIGGER PLAM_EMPLEADOS_TGR01 BEFORE
  INSERT ON PLAM_EMPLEADOS FOR EACH ROW
BEGIN
  IF :NEW.EMP_ID IS NULL OR :NEW.EMP_ID <= 0 THEN
    :NEW.EMP_ID := PLAM_EMPLEADOS_SEQ01.NEXTVAL;
  END IF;
END;
/

CREATE OR REPLACE TRIGGER PLAM_EMPLEADOS_TGR02 AFTER
  UPDATE OF EMP_ID ON PLAM_EMPLEADOS FOR EACH ROW
BEGIN
  RAISE_APPLICATION_ERROR(-20010, 'Cannot update column emp_id in table plam_empleados as it uses sequence.');
END;
/

-- Trigger for sequence plam_tipoplanillas_sec01 for column tpla_id in table plam_tipoplanillas ---------
CREATE OR REPLACE TRIGGER PLAM_TIPOPLANILLAS_TGR01 BEFORE
  INSERT ON PLAM_TIPOPLANILLAS FOR EACH ROW
BEGIN
  IF :NEW.TPLA_ID IS NULL OR :NEW.TPLA_ID <= 0 THEN
    :NEW.TPLA_ID := PLAM_TIPOPLANILLAS_SEQ01.NEXTVAL;
  END IF;
END;
/

CREATE OR REPLACE TRIGGER PLAM_TIPOPLANILLAS_TGR02 AFTER
  UPDATE OF TPLA_ID ON PLAM_TIPOPLANILLAS FOR EACH ROW
BEGIN
  RAISE_APPLICATION_ERROR(-20010, 'Cannot update column tpla_id in table plam_tipoplanillas as it uses sequence.');
END;
/

-- Create foreign keys (relationships) section -------------------------------------------------

ALTER TABLE PLAM_EMPLEADOSPLANILLA ADD CONSTRAINT PLAM_EMPLEADOSPLANILLA_FK01 FOREIGN KEY (EXP_IDTPLA) REFERENCES PLAM_TIPOPLANILLAS (TPLA_ID);

ALTER TABLE PLAM_EMPLEADOSPLANILLA ADD CONSTRAINT PLAM_EMPLEADOSPLANILLA_FK02 FOREIGN KEY (EXP_IDEMP) REFERENCES PLAM_EMPLEADOS (EMP_ID);