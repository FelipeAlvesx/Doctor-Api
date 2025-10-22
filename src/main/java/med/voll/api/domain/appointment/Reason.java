package med.voll.api.domain.appointment;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Reason {
    LATE_ARRIVAL,
    NO_SHOW,
    PATIENT_REQUEST,
    DOCTOR_UNAVAILABLE,
    EMERGENCY,
    DOUBLE_BOOKING,
    WEATHER_CONDITIONS,
    TRANSPORTATION_ISSUES,
    ILLNESS,
    OTHER;


    @JsonCreator
    public static Reason from(String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        return Reason.valueOf(value.toUpperCase());
    }

}


