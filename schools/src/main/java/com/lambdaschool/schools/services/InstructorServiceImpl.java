package com.lambdaschool.schools.services;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lambdaschool.schools.exceptions.ResourceNotFoundException;
import com.lambdaschool.schools.models.Instructor;
import com.lambdaschool.schools.models.Slips;
import com.lambdaschool.schools.models.SlipsElement;
import com.lambdaschool.schools.models.slip;
import com.lambdaschool.schools.repositories.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Service(value = "instructorService")
@JsonIgnoreProperties(ignoreUnknown = true)
public class InstructorServiceImpl implements InstructorService
{
    @Autowired
    private InstructorRepository instructorrepos;

    @Override
    public Instructor addAdvice(long id)
    {
        Instructor updatedInstructor = instructorrepos.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Instructor id " + id + " not found!"));

        RestTemplate restTemp = new RestTemplate();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
        restTemp.getMessageConverters().add(converter);

        String requestURL ="https://api.adviceslip.com/advice";
        ParameterizedTypeReference<slip> responseType = new ParameterizedTypeReference<slip>()
        {
        };

        ResponseEntity<slip> responseEntity = restTemp.exchange(requestURL, HttpMethod.GET,null,responseType);

        slip slibobj = responseEntity.getBody();

        updatedInstructor.setAdvice(slibobj.getAdvice());

        return updatedInstructor;
    }

    @Override
    public Instructor addAdvice(long id, String searchTerm)
    {

        Instructor updatedInstructor = instructorrepos.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Instructor id " + id + " not found!"));

        RestTemplate restTemp = new RestTemplate();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
        restTemp.getMessageConverters().add(converter);

        String requestURL ="https://api.adviceslip.com/advice" + searchTerm;
        ParameterizedTypeReference<Slips> responseType = new ParameterizedTypeReference<Slips>()
        {
        };

        ResponseEntity<Slips> responseEntity = restTemp.exchange(requestURL, HttpMethod.GET,null,responseType);

        SlipsElement slipsElem = responseEntity.getBody().getSlips().get(0);

        updatedInstructor.setAdvice(slipsElem.getAdvice());

        return updatedInstructor;
    }
}
