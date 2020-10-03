package com.phos.email.repositories;

import com.phos.email.models.EmailModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Folarin on 02/10/2020
 */
public interface EmailRepository extends JpaRepository<EmailModel,Long> {

    List<EmailModel> findAllByEmailContainingOrNameContainingOrMessageContainingOrderByIdDesc(String parm1, String parm2, String parm3);
}
