package com.edgeapps.personalcabinet.taxpayer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class Taxpayer {
    @JsonIgnore
    private String surname;
    private String name;
    private String middlename;
}
