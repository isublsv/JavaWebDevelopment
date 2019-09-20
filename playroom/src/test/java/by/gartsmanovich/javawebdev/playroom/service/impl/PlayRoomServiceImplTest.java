package by.gartsmanovich.javawebdev.playroom.service.impl;

import by.gartsmanovich.javawebdev.playroom.bean.param.Color;
import by.gartsmanovich.javawebdev.playroom.bean.param.Material;
import by.gartsmanovich.javawebdev.playroom.bean.toy.Doll;
import by.gartsmanovich.javawebdev.playroom.bean.toy.Toy;
import by.gartsmanovich.javawebdev.playroom.service.PlayRoomService;
import by.gartsmanovich.javawebdev.playroom.service.exception.ServiceException;
import by.gartsmanovich.javawebdev.playroom.service.factory.ServiceFactory;
import org.testng.annotations.Test;

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