package fightinggame.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Assertions;

public class VectorTest {
    private double validVx;
    private double validVy;
    private double validAx;
    private double validAy;
    private int leftDirection;
    private int rightDirection;

    private double nonValidVx;
    private double nonValidVy;
    private double nonValidAx;
    private double nonValidAy;

    private Vector leftVector;
    private Vector rightVector;
    private Vector standardVec;

    @BeforeEach
    public void setup() {
        validVx = 10;
        validVy = 10;
        validAx = 10;
        validAy = 10;
        leftDirection = -1;
        rightDirection = 1;

        leftVector = new Vector(validVx, validVy, validAx, validAy, leftDirection);
        rightVector = new Vector(validVx, validVy, validAx, validAy, rightDirection);
        standardVec = new Vector();
    }
    @Test
    @DisplayName("Check if the Constructor works ")        
    public void testConstructor() {
        // clean test for Constructor
        
        try {
            leftVector = new Vector(validVx, validVy, validAx, validAy, leftDirection);
            rightVector = new Vector(validVx, validVy, validAx, validAy, rightDirection);
            rightVector = new Vector(validVx, validVy, validAx, validAy);
            Vector vectorFromLeftVector = new Vector(leftVector);
            Vector vectorFromRightVector = new Vector(rightVector);

            standardVec = new Vector();
        }
        catch(Exception e) {
            System.out.println(e.getLocalizedMessage());
            fail();
        }
        // check values for leftVector and rightVector
        assertEquals(-validVx, leftVector.getVx() , "did not set correct cordinate");
        assertEquals(-validVy, leftVector.getVy() , "did not set correct cordinate");
        assertEquals(-validAx, leftVector.getAx() , "did not set correct cordinate");
        assertEquals(-validAy, leftVector.getAy() , "did not set correct cordinate");

        assertEquals(validVx, rightVector.getVx() , "did not set correct cordinate");
        assertEquals(validVy, rightVector.getVy() , "did not set correct cordinate");
        assertEquals(validAx, rightVector.getAx() , "did not set correct cordinate");
        assertEquals(validAy, rightVector.getAy() , "did not set correct cordinate");
        
        // empty constructor
        assertEquals(0, standardVec.getVx() , "did not set correct cordinate");
        assertEquals(0, standardVec.getVy() , "did not set correct cordinate");
        assertEquals(0, standardVec.getAx() , "did not set correct cordinate");
        assertEquals(0, standardVec.getAy() , "did not set correct cordinate");


    }
    
    @Test
    @DisplayName("check that the vector adds the new vector to values")
    public void testAddVector() {
        for (int i = 1; i < 100; i++) {
            standardVec.addVector(rightVector);

            assertEquals(i * validVx, standardVec.getVx() , "did not add correctly");
            assertEquals(i * validVy, standardVec.getVy() , "did not add correctly");
            assertEquals(i * validAx, standardVec.getAx() , "did not add correctly");
            assertEquals(i * validAy, standardVec.getAy() , "did not add correctly");
        }

        standardVec = new Vector();
        for (int i = 1; i < 100; i++) {
            standardVec.addVector(leftVector);
            assertEquals(i * (-validVx), standardVec.getVx() , "did not add correctly");
            assertEquals(i * (-validVy), standardVec.getVy() , "did not add correctly");
            // assertEquals(i * (-validAx), standardVec.getAx() , "did not add correctly");
            // assertEquals(i * (-validAy), standardVec.getAy() , "did not add correctly");
        }
        standardVec = new Vector();
        standardVec.addVector(leftVector);
        standardVec.addVector(rightVector);

        assertEquals(0, standardVec.getVx() , "did not add correctly");
        assertEquals(0, standardVec.getVy() , "did not add correctly");
        assertEquals(0, standardVec.getAx() , "did not add correctly");
        assertEquals(0, standardVec.getAy() , "did not add correctly");
    }

    @Test 
    @DisplayName("Check if subtraction works")
    public void testRemoveVector() {
        // Remove it self, should be zero in all fields
        rightVector.removeVector(rightVector);
        assertEquals(0, rightVector.getVx(), "Should change");
        assertEquals(0, rightVector.getVy(), "Should change");
        assertEquals(0, rightVector.getAx(), "Should change");
        assertEquals(0, rightVector.getAy(), "Should change");

        // Standard is zero in all fields should not change leftVector
        leftVector.removeVector(standardVec);
        assertEquals(-validVx, leftVector.getVx(), "Should not change");
        assertEquals(-validVy, leftVector.getVy(), "Should not change");
        assertEquals(-validAx, leftVector.getAx(), "Should not change");
        assertEquals(-validAy, leftVector.getAy(), "Should not change");
    }

    @Test
    @DisplayName("Check if set direction works")
    public void testSetDirection() {
        rightVector.setDirection(leftDirection);
        assertEquals(-validVx, rightVector.getVx(), "Should change, shall be negativ");
        assertEquals(-validVy, rightVector.getVy(), "Should change, shall be negativ");
        assertEquals(-validAx, rightVector.getAx(), "Should change, shall be negativ");
        assertEquals(-validAy, rightVector.getAy(), "Should change, shall be negativ");
        assertEquals(leftDirection, rightVector.getDirection());

        rightVector.setDirection(rightDirection);
        assertEquals(validVx, rightVector.getVx(), "Should be positive");
        assertEquals(validVy, rightVector.getVy(), "Should be positive");
        assertEquals(validAx, rightVector.getAx(), "Should be positive");
        assertEquals(validAy, rightVector.getAy(),"Should be positive");
        assertEquals(rightDirection, rightVector.getDirection());
    }

    @Test
    @DisplayName("check if applyAcceleration adjusts velocity correctly")
    public void testApplyAcceleration() {
        // Try to apply zero acceleration
        standardVec.applyAcceleration();
        assertEquals(0, standardVec.getVx() , "did not add correctly");
        assertEquals(0, standardVec.getVy() , "did not add correctly");
        assertEquals(0, standardVec.getAx() , "did not add correctly");
        assertEquals(0, standardVec.getAy() , "did not add correctly");

        rightVector.applyAcceleration();
        assertEquals(validVx + validAx, rightVector.getVx() , "did not add correctly");
        assertEquals(validVy + validAy, rightVector.getVy() , "did not add correctly");
        assertEquals(validAx, rightVector.getAx() , "did not add correctly");
        assertEquals(validAy, rightVector.getAy() , "did not add correctly");
    }

    @Test
    @DisplayName("check if applyAcceleration adjusts velocity correctly")
    public void testSetters() { 
        standardVec.setVx(validVx);
        standardVec.setVy(validVy);
        standardVec.setAx(validAx);
        standardVec.setAy(validAy);
        
        assertEquals(validVx, standardVec.getVx() , "did not set correctly");
        assertEquals(validVy, standardVec.getVy() , "did not set correctly");
        assertEquals(validAx, standardVec.getAx() , "did not set correctly");
        assertEquals(validAy, standardVec.getAy() , "did not set correctly");

    


    }
}
