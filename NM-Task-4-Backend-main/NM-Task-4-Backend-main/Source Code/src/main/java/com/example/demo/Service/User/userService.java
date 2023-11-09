package com.example.demo.Service.User;

import com.example.demo.Config.jwtService;
import com.example.demo.DTO.LoginResponse;
import com.example.demo.DTO.UserDto;
import com.example.demo.DTO.UserOrderDTO;
import com.example.demo.Model.*;
import com.example.demo.DAO.AddressRepository;
import com.example.demo.DAO.ConfirmationTokenRepository;
import com.example.demo.DAO.UserRepository;
import com.example.demo.Service.Email.EmailService;
import jakarta.transaction.Transactional;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Builder
@Data
public class userService implements UserService {


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private  AddressRepository addressRepository;

    PasswordEncoder passwordEncoder;
    @Autowired
    private jwtService JwtService;
    @Autowired
    private  AuthenticationManager authenticationManager;
    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;
    @Autowired
    EmailService emailService;
    @Override
    @Transactional
    public ResponseEntity<?> userSave(UserDto userDto) {
        if (userRepository.existsByUserEmail(userDto.getUserEmail())) {
           return   ResponseEntity.badRequest().body("Error: Email is already in use!");

        }
                Address newaddress=new Address();
                newaddress.setAddressOne(userDto.getAddressOne());
                newaddress.setAddressTwo(userDto.getAddressTwo());
                newaddress.setAddressThree(userDto.getAddressThree());
                newaddress.setCity(userDto.getCity());
                newaddress.setCountry(userDto.getCountry());
                newaddress.setState(userDto.getState());
                newaddress.setPinCode(userDto.getPinCode());
                addressRepository.save(newaddress);
                var userDetails= UserDetail.builder()
                .userName(userDto.getUserName())
                .userEmail(userDto.getUserEmail())
                .userMobileNumber(userDto.getUserMobileNumber())
                .role(Role.USER)
                .password(passwordEncoder.encode(userDto.getPassword()))
               .address(newaddress)
                .build();
                userRepository.save(userDetails);
                var jwt=JwtService.generateToken(userDetails);
                //   return LoginResponse.builder().token(jwt).build();
                ConfirmationToken confirmationToken = new ConfirmationToken(userDto);
                confirmationTokenRepository.save(confirmationToken);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(userDetails.getUserEmail());
        mailMessage.setSubject("Complete Registration!");
        mailMessage.setText("To confirm your account, please click here : "
                +"http://localhost:8080/api/confirm-account?token="+confirmationToken.getConfirmationToken());
        emailService.sendEmail(mailMessage);

        System.out.println("Confirmation Token: " + confirmationToken.getConfirmationToken());

       return ResponseEntity.ok("Verify email by the link sent on your email address");

    }
    @Override
    public LoginResponse authenticate(login request) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByUserEmail(request.getEmail()).orElseThrow();
        var jwt = JwtService.generateToken(user);
        return LoginResponse.builder().token(jwt).build();

    }

    @Override
    public ResponseEntity<?> confirmEmail(String confirmationToken) {
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

        if(token != null)
        {
           // UserDetail user = userRepository.findByUserEmailIgnoreCase(token.getUserEntity().getUserEmail());
           // user.setEnabled(true);
           // userRepository.save(user);
            System.out.println("email verification ");
            return ResponseEntity.ok("Email verified successfully!");
        }
        return ResponseEntity.badRequest().body("Error: Couldn't verify email");
    }

    @Override
    public UserDetail getUserId(String userName) {
      return  userRepository.findByUserName(userName);
    }

    @Override
    public UserOrderDTO getUser(Integer id) {
        return userRepository.findByUserID(id);
    }

//    @Override
//    public UserOrderDTO getUserDetails(String userName) {
//        return (UserOrderDTO) userRepository.findUserDetail(userName);
//    }


}



//    UserDetail userDetails=new UserDetail();
////       userDetails.setUserEmail(userDto.getUserEmail());
////       userDetails.setUserName(userDto.getUserName());
////       userDetails.setUserMobileNumber(userDto.getUserMobileNumber());
////       userDetails.setPassword(userDto.getPassword());
//
//
//    Address naddress=new Address();
//        naddress.setAddressOne(userDto.getAddressOne());
//                naddress.setAddressTwo(userDto.getAddressTwo());
//                naddress.setAddressThree(userDto.getAddressThree());
//                naddress.setCity(userDto.getCity());
//                naddress.setCountry(userDto.getCountry());
//                naddress.setState(userDto.getState());
//                naddress.setPinCode(userDto.getPinCode());
//                addressRepository.save(naddress);
//                var userDetails= UserDetail.builder()
//                .userName(userDto.getUserName())
//                .userEmail(userDto.getUserEmail())
//                .userMobileNumber(userDto.getUserMobileNumber())
//                .role(Role.USER)
//                .password(passwordEncoder.encode(userDto.getPassword()))
//                .address(naddress)
//                .build();
//                //  userDetails.setAddress(address);
//                userRepository.save(userDetails);
//                var jwt=JwtService.generateToken(userDetails);
//                return LoginResponse.builder().token(jwt).build();

//    // Inject EntityManager
//
//
//    // Create a new Address entity
//    Address newAddress = new Address();
//            newAddress.setAddressOne(userDto.getAddressOne());
//                    newAddress.setAddressTwo(userDto.getAddressTwo());
//                    newAddress.setAddressThree(userDto.getAddressThree());
//                    newAddress.setCity(userDto.getCity());
//                    newAddress.setCountry(userDto.getCountry());
//                    newAddress.setState(userDto.getState());
//                    newAddress.setPinCode(userDto.getPinCode());
//
//                    // Save the new Address entity
//                    newAddress = addressRepository.save(newAddress);
//
//                    // Create a new UserDetail entity and associate it with the Address
//                    UserDetail userDetails = UserDetail.builder()
//                    .userName(userDto.getUserName())
//                    .userEmail(userDto.getUserEmail())
//                    .userMobileNumber(userDto.getUserMobileNumber())
//                    .role(Role.USER)
//                    .password(passwordEncoder.encode(userDto.getPassword()))
//                    .address(newAddress)
//                    .build();
//
//                    // Use EntityManager to merge the Address entity back into the persistence context
//                    newAddress = entityManager.merge(newAddress);
//
//                    // Save the UserDetail entity
//                    userDetails = userRepository.save(userDetails);
//
//                    // Generate the JWT token and return it in the response
//                    var jwt = JwtService.generateToken(userDetails);
//                    return LoginResponse.builder().token(jwt).build();