
package com.golovackii.FileStorage.repository;

import com.golovackii.FileStorage.model.FileUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileUserRepository extends JpaRepository<FileUser, Long> {

}
