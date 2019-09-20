package by.gartsmanovich.java_web_dev.playroom.service.impl;

import by.gartsmanovich.java_web_dev.playroom.bean.param.Color;
import by.gartsmanovich.java_web_dev.playroom.bean.param.Material;
import by.gartsmanovich.java_web_dev.playroom.bean.toy.Doll;
import by.gartsmanovich.java_web_dev.playroom.bean.toy.Toy;
import by.gartsmanovich.java_web_dev.playroom.service.PlayRoomService;
import by.gartsmanovich.java_web_dev.playroom.service.exception.ServiceException;
import by.gartsmanovich.java_web_dev.playroom.service.factory.ServiceFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class PlayRoomServiceImplTest {

    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private PlayRoomService<Toy> playRoomService =
            serviceFactory.getPlayRoomService();

    private Toy doll = new Doll(-1, "Doll Masha", Color.MULTICOLORED, 4,
            22.99, Material.PLASTIC, 220.0);

    @Test(expectedExceptions = ServiceException.class)
    public void testAddNewEntity() throws ServiceException {
        playRoomService.addNewEntity(doll);
    }

    @Test
    public void testDeleteEntityByID() {
    }

    @Test
    public void testFindEntityByID() {
    }

    @Test
    public void testFindEntityByTitle() {
    }

    @Test
    public void testFindEntityByFirstTitleLetter() {
    }

    @Test
    public void testFindEntityByRangeID() {
    }
}