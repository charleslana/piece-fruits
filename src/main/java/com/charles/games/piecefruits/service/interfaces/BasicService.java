package com.charles.games.piecefruits.service.interfaces;

import com.charles.games.piecefruits.config.exceptions.BusinessRuleException;
import com.charles.games.piecefruits.model.dto.ResponseDTO;

public interface BasicService {

    BusinessRuleException getException(String message);

    ResponseDTO getSuccess(String message);
}
