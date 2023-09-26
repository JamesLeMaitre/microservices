package com.esmc.mobileMoneyCredit.repositories;



import com.esmc.mobileMoneyCredit.entities.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttachmentRepository extends JpaRepository<Attachment, String> {
}
