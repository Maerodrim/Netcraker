package com.example.DataRoadsAndWeather.Dto.Ext;

import com.example.DataRoadsAndWeather.Dto.SingleResponseObjectDto;
import com.example.DataRoadsAndWeather.Dto.Status.StatusCodeEnum;
import com.example.DataRoadsAndWeather.Dto.Status.StatusEnum;
import com.example.DataRoadsAndWeather.Dto.View.SingleResponseObjectDtoView;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.extern.log4j.Log4j2;


@Log4j2
public final class SingleResponseObjectDtoExt<T> extends SingleResponseObjectDto {

    @JsonView(SingleResponseObjectDtoView.StatusCode.class)
    private StatusCodeEnum statusCode;

    @JsonView(SingleResponseObjectDtoView.Message.class)
    private String message;

    @JsonView(SingleResponseObjectDtoView.Success.class)
    private Boolean isSuccess;

    @JsonView(SingleResponseObjectDtoView.DataOrException.class)
    private T dataOrException;

    public SingleResponseObjectDtoExt(StatusEnum status, String message, Boolean success, T dataOrException) {

        this.statusCode = this.getErrorCodeEnum(status);
        this.message = message;
        this.isSuccess = success;
        this.dataOrException = dataOrException;

        log.trace("Response dto has created, status: " + status);
    }
}
