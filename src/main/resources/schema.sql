/***************************************************************************************
 *  CUSTOMER TABLE                                                                     *
 *  Customer data is stored in this table
 *  Customer table has 3 columns CUSTOMER_ID, NAME, ACTIVE_POINTS                                       *
 ***************************************************************************************/



CREATE TABLE CUSTOMER(CUSTOMER_ID LONG NOT NULL PRIMARY KEY ,
                      NAME VARCHAR(20),ACTIVE_POINTS INTEGER);
/***************************************************************************************
 *  PRODUCT TABLE
 *  Product data is stored here
 *  Table has 3 columns PRODUCT_ID, ACTIVE_POINTS,COLUMN_ID
 ***************************************************************************************/

CREATE TABLE PRODUCT(PRODUCT_ID LONG NOT NULL PRIMARY KEY,
                     ACTIVE_POINTS INTEGER NOT NULL ,
                     CUSTOMER_ID LONG NOT NULL REFERENCES CUSTOMER(CUSTOMER_ID)
)
