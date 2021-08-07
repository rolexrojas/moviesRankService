package com.backbase.moviesRankService.processor;

import com.backbase.moviesRankService.types.AcademyAwardsDTO;
import org.springframework.batch.item.ItemProcessor;

public class AcademyAwardItemProcessor implements ItemProcessor<AcademyAwardsDTO, AcademyAwardsDTO> {


    @Override
    public AcademyAwardsDTO process(AcademyAwardsDTO item) throws Exception {
        //TODO: extract data and load in the extra fields
        System.out.println("additional size => " + item.getAdditionalInfo().length());
        if(item.getAdditionalInfo().length() > 200){
            System.out.println("additional size => " + item.getAdditionalInfo().length());
        }
        return item;
    }
}
