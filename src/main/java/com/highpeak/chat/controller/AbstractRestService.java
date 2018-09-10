package com.highpeak.chat.controller;

import com.highpeak.chat.exception.DataException;
import com.highpeak.chat.uiresponse.UIErrorMessage;
import com.highpeak.chat.uiresponse.UIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class AbstractRestService {

    /**
     * Method to build success message response
     *
     * @param msg
     * @return
     */
    protected ResponseEntity<UIResponse<String>> buildSuccessMessage(final String msg) {
        final UIResponse<String> message = new UIResponse<>();
        message.setEntity("iiot.success");
        message.setMessage(msg);
        message.setStatus(HttpStatus.OK.value());
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    /**
     * Method to build success message response
     *
     * @param t
     * @return
     */
    protected <T> ResponseEntity<UIResponse<T>> buildResponse(final T t) {
        final UIResponse<T> uiResponse = new UIResponse<>(t);
        uiResponse.setStatus(HttpStatus.OK.value());
        return new ResponseEntity<>(uiResponse, HttpStatus.OK);
    }

    /**
     * Method to build error response for data exception
     *
     * @param e
     * @return
     */
    @SuppressWarnings("rawtypes")
    public ResponseEntity<UIErrorMessage> buildError(final DataException e) {
        final UIErrorMessage message = new UIErrorMessage();
        message.setMessageCode(e.getErrorCode());
        message.setMessage(e.getErrorMessage());
        if (e.getHttpStatus().equals(HttpStatus.BAD_REQUEST)) {
            message.setStatus(HttpStatus.BAD_REQUEST.value());
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
        if (e.getHttpStatus().equals(HttpStatus.FORBIDDEN)) {
            message.setStatus(HttpStatus.FORBIDDEN.value());
            return new ResponseEntity<>(message, HttpStatus.FORBIDDEN);
        }
        if (e.getHttpStatus().equals(HttpStatus.NOT_FOUND)) {
            message.setStatus(HttpStatus.NOT_FOUND.value());
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
        if (e.getHttpStatus().equals(HttpStatus.CONFLICT)) {
            message.setStatus(HttpStatus.CONFLICT.value());
            return new ResponseEntity<>(message, HttpStatus.CONFLICT);
        }
        message.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
