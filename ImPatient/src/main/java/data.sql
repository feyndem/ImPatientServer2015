INSERT INTO usercredential(id, password, userid, username, userrole, userrolevalue) VALUES (1, 'pass', 'ImPa1', 'ImPa1 LN1', 'PATIENT', 200);
INSERT INTO patient(recordnumber, firstname, lastname, username) VALUES (1, 'Impa1', 'LN1', 'ImPa1 LN1');

INSERT INTO usercredential(id, password, userid, username, userrole, userrolevalue) VALUES (2, 'pass', 'ImPa2', 'ImPa LN2', 'PATIENT', 200);
INSERT INTO patient(recordnumber, firstname, lastname, username) VALUES (2, 'Impa2', 'LN2', 'ImPa LN2');

INSERT INTO usercredential(id, password, userid, username, userrole, userrolevalue) VALUES (3, 'pass', 'ImPa3', 'ImPa3 LN3', 'PATIENT', 200);
INSERT INTO patient(recordnumber, firstname, lastname, username) VALUES (3, 'Impa3', 'LN3', 'ImPa3 LN3');

INSERT INTO usercredential(id, password, userid, username, userrole, userrolevalue) VALUES (4, 'pass', 'Admin1', 'Admin1 AD1', 'ADMIN', 500);    

INSERT INTO appointment(id, appointmenttime, patient_recordnumber, arrivalcheck, treatmentcheck, treatedcheck) VALUES (1, LOCALTIMESTAMP + INTERVAL '30 minute', 1, true, false, false);
INSERT INTO appointment(id, appointmenttime, patient_recordnumber, arrivalcheck, treatmentcheck, treatedcheck) VALUES (2, LOCALTIMESTAMP + INTERVAL '45 minute', 2, true, false, false);
INSERT INTO appointment(id, appointmenttime, patient_recordnumber, arrivalcheck, treatmentcheck, treatedcheck) VALUES (3, LOCALTIMESTAMP + INTERVAL '60 minute', 3, false, false, false);

