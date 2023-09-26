package com.esmc.gestionCertification.enums;

public enum CheckTraitementEnum {

        PENDING(0),
        VALIDATE(1),
        ERROR(2);

        private int value;

    CheckTraitementEnum(int value) {
            this.value = value;
        }



}
