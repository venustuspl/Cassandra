package pl.venustus.cassandra.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import pl.venustus.cassandra.model.Message;

import java.util.List;
import java.util.UUID;

public interface MessageRepository extends CassandraRepository<Message, UUID> {
    List<Message> deleteByMagicNumber(Integer magicnumber);

    @Query("SELECT * FROM VMESSAGE WHERE EMAIL = :email")
    List<Message> findByEmail(String email);

}