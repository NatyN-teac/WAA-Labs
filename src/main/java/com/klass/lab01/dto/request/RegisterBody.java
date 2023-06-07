package com.klass.lab01.dto.request;

import com.klass.lab01.domain.Role;
import lombok.Data;

@Data
public class RegisterBody {
    String name;
    String password;
    String email;
    String role;
}
