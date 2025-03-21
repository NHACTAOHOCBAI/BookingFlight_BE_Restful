package com.bookingflight.demo.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.bookingflight.demo.dto.request.SeatClassRequest;
import com.bookingflight.demo.dto.response.SeatClassResponse;
import com.bookingflight.demo.entity.SeatClass;
import com.bookingflight.demo.exception.AppException;
import com.bookingflight.demo.exception.ErrorCode;
import com.bookingflight.demo.mapper.SeatClassMapper;
import com.bookingflight.demo.repository.SeatClassRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SeatClassService {
    private final SeatClassRepository seatClassRepository;
    private final SeatClassMapper seatClassMapper;

    public List<SeatClassResponse> getAllSeatClasses() {
        return seatClassRepository.findAll()
                .stream()
                .map(seatClassMapper::toResponse)
                .collect(Collectors.toList());
    }

    public SeatClassResponse getSeatClassByID(String id) {
        return seatClassRepository.findById(id)
                .map(seatClassMapper::toResponse)
                .orElseThrow(() -> new AppException(ErrorCode.SEATCLASS_NOT_EXISTED));
    }

    public SeatClassResponse createSeatClass(SeatClassRequest request) {
        if (seatClassRepository.existsByClassName(request.getClassName())) {
            throw new AppException(ErrorCode.SEATCLASS_NAME_EXISTED);
        }
        return seatClassMapper.toResponse(seatClassRepository.save(seatClassMapper.toEntity(request)));
    }

    public SeatClassResponse updateSeatClass(String id, SeatClassRequest request) {
        SeatClass seatClass = seatClassRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.SEATCLASS_NOT_EXISTED));
        SeatClassMapper.updateSeatClass(seatClass, request);
        return seatClassMapper.toResponse(seatClassRepository.save(seatClass));
    }

    public void deleteSeatClass(String id) {
        seatClassRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.SEATCLASS_NOT_EXISTED));

        seatClassRepository.deleteById(id);
    }

}
