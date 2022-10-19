package com.example.etsproject.service.concrete;

import com.example.etsproject.core.business.BusinessRules;
import com.example.etsproject.core.image.CloudinaryManager;
import com.example.etsproject.core.image.ImageService;
import com.example.etsproject.entity.Customer;
import com.example.etsproject.repository.CustomerRepository;
import com.example.etsproject.service.abstracts.CustomerService;
import com.example.etsproject.service.abstracts.TokenValidationService;
import com.example.etsproject.utils.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CustomerServiceManager implements CustomerService {

    private final CustomerRepository customerRepository;
    @Autowired
    private TokenValidationService tokenValidationService;

    private Result validation(int customerId){
        var result = tokenValidationService.customerIdAndTokenEmailVerification(customerId);
        if (!result.isSuccess()){
            return new ErrorResult(result.getMessage());
        }
        return new SuccessResult();
    }

    private Result isSupportedContentType(String contentType) {
        if (contentType.equals("image/png") || contentType.equals("image/jpg") || contentType.equals("image/jpeg")) {
            return new SuccessResult();
        }
        return new ErrorResult("Lütfen sadece PNG, JPG veya JPEG dosya yükleyiniz.");
    }

    @Override
    public DataResult<List<Customer>> findAll() {
        return new SuccessDataResult<>(customerRepository.findAll());
    }

    @Override
    public DataResult<Customer> findById(int id) {
        var result = customerRepository.findCustomerById(id);
        if(result == null){
            return new ErrorDataResult<>("Kullanıcı bulunamadı.");
        }
        return new SuccessDataResult<>(result);
    }

    @Override
    public Customer findByEmail(String email) {
        return customerRepository.findCustomerByEmail(email);
    }

    @Override
    public Result imageUpdate(int id, MultipartFile file) {
        var result = findById(id);
        if(!result.isSuccess()){
            return new ErrorResult(result.getMessage());
        }
        var rule = BusinessRules.run(validation(id));
        if (rule != null){
            return new ErrorResult(rule.getMessage());
        }
        var ctrl = BusinessRules.run(isSupportedContentType(file.getContentType()));
        if (ctrl != null){
            return new ErrorResult(ctrl.getMessage());
        }
        ImageService service = new CloudinaryManager();
        Map<String, String> upload = (Map<String, String>) service.uploadImage(file).getData();
        result.getData().setProfileImage(upload.get("url"));
        customerRepository.save(result.getData());
        return new SuccessResult("Profil resmi güncellendi.");
    }

    @Override
    public Result add(Customer customer) {
        customerRepository.save(customer);
        return new SuccessResult("Kullanıcı başaıyla kaydedildi.");
    }

    @Override
    public DataResult<Customer> getByEmail(String email) {
        var result = findByEmail(email);
        if (result == null){
            return new ErrorDataResult<>("Kullanıcı bulunamadı.");
        }
        return new SuccessDataResult<>(result);
    }

    @Override
    public Result update(Customer customer) {
        var rule = BusinessRules.run(validation(customer.getId()));
        if (rule != null){
            return new ErrorResult(rule.getMessage());
        }
        customerRepository.save(customer);
        return new SuccessResult("Kullanıcı başarıyla güncellendi.");
    }

    @Override
    public Result delete(int id) {
        var rule = BusinessRules.run(validation(id));
        if (rule != null){
            return new ErrorResult(rule.getMessage());
        }
        customerRepository.delete(findById(id).getData());
        return new SuccessResult("Kullanıcı başarıyla silindi");
    }
}
