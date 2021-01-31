package pl.venustus.Cassandra.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import pl.venustus.Cassandra.model.Message;


public interface MessageRepository extends CassandraRepository<Message, UUID> {

    @Query("SELECT * FROM message  WHERE magic_number = :magic_number")
    @AllowFiltering
    Optional<Message> findByMagic_number (@Param("magic_number") Integer magic_number);

    @Modifying
    @AllowFiltering
    @Query("DELETE FROM message  WHERE magic_number = :magic_number")
    Optional<Message> deleteByMagic_number (@Param("magic_number") Integer magic_number);
}