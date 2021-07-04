INSERT INTO MODEL (ID, MODEL) SELECT * FROM CSVREAD('classpath:test-scripts/model.csv', null, 'charset=UTF-8 fieldSeparator=;');
INSERT INTO BRAND (ID, BRAND) SELECT * FROM CSVREAD('classpath:test-scripts/brand.csv', null, 'charset=UTF-8 fieldSeparator=;');
INSERT INTO VEHICLE (ID, AVERAGE_CITY_CONSUMPTION, AVERAGE_HIGHWAY_CONSUMPTION, FABRICATION_DATE, NAME, BRAND_ID, MODEL_ID) SELECT * FROM CSVREAD('classpath:test-scripts/vehicle.csv', null, 'charset=UTF-8 fieldSeparator=;');
