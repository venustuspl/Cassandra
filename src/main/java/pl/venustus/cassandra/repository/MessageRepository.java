package pl.venustus.cassandra.repository;

import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;
import pl.venustus.cassandra.model.Message;

import java.util.List;
import java.util.UUID;


public interface MessageRepository extends CassandraRepository<Message, UUID> {
    @AllowFiltering
    List<Message> findByMagicNumber(Integer magicnumber);

    List<Message> deleteByMagicNumber(Integer magicnumber);

    @AllowFiltering
    List<Message> findByEmail(String email);
}