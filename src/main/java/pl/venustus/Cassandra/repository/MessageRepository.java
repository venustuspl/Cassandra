package pl.venustus.Cassandra.repository;

import com.datastax.oss.driver.api.mapper.annotations.Delete;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;
import pl.venustus.Cassandra.model.Message;

import java.util.List;
import java.util.UUID;


public interface MessageRepository extends CassandraRepository<Message, UUID> {

//    @AllowFiltering
//    @Query("SELECT * FROM message    WHERE magic_number = :magic_number")
//    Optional<Message> findByMagic_number (@Param("magic_number") Integer magic_number);

    @AllowFiltering
    List<Message> findByMagicNumber(Integer magicnumber);

    List<Message> deleteByMagicNumber(Integer magicnumber);

    @Delete
    void delete(Message message);

    @AllowFiltering
    List<Message> findByEmail(String email);
}