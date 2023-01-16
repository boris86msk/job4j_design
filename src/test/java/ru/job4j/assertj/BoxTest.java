package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BoxTest {

    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
    }

    @Test
    void isThisTetrahedron() {
        Box box = new Box(4, 6);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Tetrahedron");
    }

    @Test
    void isThisCube() {
        Box box = new Box(8, 12);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Cube");
    }

    @Test
    void itIsNotCube() {
        Box box = new Box(0, 12);
        String name = box.whatsThis();
        assertThat(name).isNotEqualTo("Cube");
    }


    @Test
    void zeroVerticesInSphere() {
        Box box = new Box(0, 10);
        int vertices = box.getNumberOfVertices();
        assertThat(vertices).isEqualTo(0);
    }

    @Test
    void eightVerticesInCube() {
        Box box = new Box(8, 12);
        int vertices = box.getNumberOfVertices();
        assertThat(vertices).isEqualTo(8);
    }

    @Test
    void notForVerticesInTetrahedron() {
        Box box = new Box(8, 12);
        int vertices = box.getNumberOfVertices();
        assertThat(vertices).isNotEqualTo(4);
    }


    @Test
    void tetrahedronIsExist() {
        Box box = new Box(4, 6);
        boolean exist = box.isExist();
        assertThat(exist).isTrue();
    }

    @Test
    void figureIsNotExist() {
        Box box = new Box(5, 5);
        boolean exist = box.isExist();
        assertThat(exist).isFalse();
    }

    @Test
    void cubeAreaIsFiftyFour() {
        Box box = new Box(8, 3);
        double area = box.getArea();
        assertThat(area).isCloseTo(54D, withPrecision(0.01D));
    }

    @Test
    void cubAreaIsFiftyFour() {
        Box box = new Box(4, 3);
        double area = box.getArea();
        assertThat(area).isCloseTo(15.58D, withPrecision(0.01D));
    }
}