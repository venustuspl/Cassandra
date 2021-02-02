package pl.venustus.cassandra.model;

import org.springframework.stereotype.Component;

@Component
public class Magic {
    private Integer magic_number;

    public Integer getMagic_number() {
        return magic_number;
    }

    public void setMagic_number(Integer magic_number) {
        this.magic_number = magic_number;
    }
}
