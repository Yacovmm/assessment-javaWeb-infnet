package com.infnet.infnet.repository;

import com.infnet.infnet.model.Product;
import com.infnet.infnet.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
}
