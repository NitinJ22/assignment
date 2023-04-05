package com.latentview.assignment.service;

import com.latentview.assignment.dto.Request;
import com.latentview.assignment.dto.Response;
import com.latentview.assignment.dto.TvShow;
import com.latentview.assignment.entity.UserData;
import com.latentview.assignment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RecommendationService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private TvShowClient showClient;

    public List<TvShow> getAllShows(){
        return this.showClient.getAllShowData().block();
    }

    public Set<String> getGenreList(){
        Set<String> genre = new HashSet<>();
        List<TvShow> tvShow =  this.showClient.getAllShowData().block();
        tvShow = tvShow.stream().filter(data -> data.getGenres().size()>0).collect(Collectors.toList());
        for (TvShow show: tvShow){
            genre.addAll(show.getGenres());
        }
        return genre;
    }

    public Response recommendTVShow(Request req) {
        Optional<UserData> optionalUser = userRepo.findById(req.getId());
        UserData user;
        if (optionalUser.isPresent()) {
            user = optionalUser.get();
            List<Long> recommendedTVShows = user.getRecommendedShowList().stream().collect(Collectors.toList());
            List<TvShow> allTVShows = showClient.getAllShowData().block();
            List<TvShow> unwatchedTVShows = new ArrayList<>(allTVShows.stream().filter(data -> data.getGenres().contains(req.getGenre())).collect(Collectors.toList()));
            List<TvShow> subList = null;
            if(req.getEndIndex()>unwatchedTVShows.size()){
                subList = unwatchedTVShows.subList(req.getStartIndex(), unwatchedTVShows.size());
            }else{
                subList = unwatchedTVShows.subList(req.getStartIndex(), req.getEndIndex());
            }

            unwatchedTVShows.removeAll(recommendedTVShows);

            if (unwatchedTVShows.isEmpty()) {
                // All TV shows have been recommended
                user.getRecommendedShowList().clear();
                userRepo.save(user);
//                unwatchedTVShows.addAll(allTVShows);
            }else{
                subList.forEach(data -> user.getRecommendedShowList().add(data.getId()));
                userRepo.save(user);
            }

//            int randomIndex = new Random().nextInt(unwatchedTVShows.size());
//            TVShow recommendedTVShow = unwatchedTVShows.get(randomIndex);
//            user.getRecommendedTVShows().add(recommendedTVShow);
//            userRepository.save(user);

            return new Response(subList, unwatchedTVShows.size(), user);
        } else {
            user = null;
        }

        return new Response(null, 0, null);
    }


    public Response onlyRecommended(Request req) {
        Optional<UserData> optionalUser = userRepo.findById(req.getId());
        UserData user = null;
        if (optionalUser.isPresent()) {
            user = optionalUser.get();
            List<Long> recommendedTVShows = user.getRecommendedShowList().stream().collect(Collectors.toList());
            List<TvShow> allTVShows = showClient.getAllShowData().block();
            List<TvShow> allRecommended = new ArrayList<>(allTVShows.stream().filter(data -> recommendedTVShows.contains(data.getId())).collect(Collectors.toList()));
            List<TvShow> subList = null;
            if(req.getEndIndex()>allRecommended.size()){
                subList = allRecommended.subList(req.getStartIndex(), allRecommended.size());
            }else{
                subList = allRecommended.subList(req.getStartIndex(), req.getEndIndex());
            }
            return new Response(subList, allRecommended.size(), user);
        }
        return new Response(null, 0, null);

    }



    public UserData resetRecommendations(Long userId) {
        Optional<UserData> optionalUser = userRepo.findById(userId);
        UserData user = null;
        if (optionalUser.isPresent()) {
            user = optionalUser.get();
            user.getRecommendedShowList().clear();
            userRepo.save(user);
        }
        return user;
    }

    public UserData resetRecommendationsNative(Long userId) {
        UserData user = userRepo.findByUserId(userId);
        try{
            if (Objects.nonNull(user)) {
                userRepo.deleteRecommendedShowListByUserId(user.getId());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }


//    @Autowired
//    private RecommendationHistoryRepository recommendationHistoryRepo;

//    public List<TvShow> getTvShow(UserData user){
//        List<TvShow> shows = (List<TvShow>) showService.getAllShowData();
//        List<RecommendationHistory> recommendationHistoryList = recommendationHistoryRepo.findByUserDataId(user.getId());
//        shows = shows.stream().filter(data -> recommendationHistoryList.contains()).
//    }

}
