package com.onlineLibrary.Online.service.service;

import com.onlineLibrary.Online.service.dto.registration.RegistrationRequestDTO;
import com.onlineLibrary.Online.service.dto.registration.RegistrationResponseDTO;


public interface RegistrationService {

    RegistrationResponseDTO register(RegistrationRequestDTO dto);
}
