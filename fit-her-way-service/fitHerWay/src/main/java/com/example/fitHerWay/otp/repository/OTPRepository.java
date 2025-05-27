

package com.example.fitHerWay.otp.repository;




import com.example.fitHerWay.otp.entity.OTP;
import com.example.fitHerWay.otp.entity.OTPPurpose;
import com.example.fitHerWay.users.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OTPRepository extends JpaRepository<OTP, Long> {
    Optional<OTP> findByUserAndOtpValue(User user, String otp);

    Optional<OTP> findByOtpValueAndPurpose(String otp, OTPPurpose purpose);
}
