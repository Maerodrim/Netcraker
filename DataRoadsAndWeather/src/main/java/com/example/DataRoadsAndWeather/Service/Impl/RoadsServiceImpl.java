package com.example.DataRoadsAndWeather.Service.Impl;

import com.example.DataRoadsAndWeather.Data.Roads;
import com.example.DataRoadsAndWeather.Dto.RoadsDto;
import com.example.DataRoadsAndWeather.Interface.RoadsRepo;
import com.example.DataRoadsAndWeather.Mappers.RoadsMapper;
import com.example.DataRoadsAndWeather.Service.RoadsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor(onConstructor=@__(@Autowired))
@Service
@Log4j2
public class RoadsServiceImpl implements RoadsService {
    private RoadsRepo roadsRepo;

    @Override
    public List<Roads> getAllRoads() throws NullPointerException {

        log.info("getAllUsers() method");

        return (List<Roads>) this.roadsRepo.findAll();
    }

    @Override
    public Roads getOnlyOneRoads(Long id) throws NullPointerException {
        return this.roadsRepo.findById(id).orElseThrow(NullPointerException::new);
    }

    @Override
    public Roads signUp(RoadsDto roadsDto) throws IllegalArgumentException {

        Roads roads = RoadsMapper.INSTANCE.toRoads(roadsDto);

        roads.setId(null);

        log.info("Сохранена погода: " + roads.toString());

        return roadsRepo.save(roads);
    }

    @Override
    public List<Roads> getAllExceptPrincipalRoads(){
        return (List<Roads>) this.roadsRepo.findAll();
    }
}
