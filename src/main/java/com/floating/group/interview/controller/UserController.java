package com.floating.group.interview.controller;

import com.floating.group.interview.dto.CandleRequest;
import com.floating.group.interview.dto.CandleResponse;
import com.floating.group.interview.dto.UserDto;
import com.floating.group.interview.exception.UserNotFoundException;
import com.floating.group.interview.model.CryptoData;
import com.floating.group.interview.model.User;
import com.floating.group.interview.repository.CryptoDataRepository;
import com.floating.group.interview.service.CoinbaseExchangeService;
import com.floating.group.interview.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    private final CryptoDataRepository cryptoDataRepository;

    private final CoinbaseExchangeService coinbaseExchangeService;

    public UserController(@Autowired UserService userService, @Autowired CryptoDataRepository cryptoDataRepository, CoinbaseExchangeService coinbaseExchangeService) {
        this.userService = userService;
        this.cryptoDataRepository = cryptoDataRepository;
        this.coinbaseExchangeService = coinbaseExchangeService;
    }

    /**
     * curl localhost:8090/fpg/user/all
     * @return list of all users available
     */
    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    /**
     * curl localhost:8090/fpg/user/1
     * @param userId
     * @return user found for the given id
     * @throws UserNotFoundException
     */
    @GetMapping("/{id}")
    public User findById(@PathVariable("id") final Long userId) throws UserNotFoundException {
        return userService.findById(userId);
    }

    /**
     * curl localhost:8090/fpg/user -H'content-type: application/json' -d'{"name":"rajeev"}'
     * @param userDto : user body to be created
     * @return created user body
     */
    @PostMapping
    public User createUser(@RequestBody final UserDto userDto) {
        return userService.save(userDto.toUser());
    }

    /**
     * curl -XDELETE localhost:8090/fpg/user/1
     * @param userId : user id to be deleted
     * @return deleted user id
     * @throws UserNotFoundException
     */
    @DeleteMapping("/{id}")
    public User deleteUser(@PathVariable("id") final Long userId) throws UserNotFoundException {
        return userService.delete(userId);
    }

    /**
     * curl -XDELETE localhost:8090/fpg/user -H'content-type: application/json' -d'[3,4]'
     * @param userIds : list of user ids to be deleted
     * @return deleted list of user ids
     */
    @DeleteMapping
    public List<User> deleteUser(@RequestBody final List<Long> userIds) {
        return userService.deleteAll(userIds);
    }

    // incorporate debugging time
    // comments in code
    // interval updates
    @PostMapping("/insert")
    public void insert() throws IOException, ParseException {
        BufferedReader br = new BufferedReader(new FileReader("/Users/300072873/Downloads/FTX_Futures_BTCPERP_minute.csv"));
        String line = "";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        while ((line = br.readLine()) != null)  {
            String[] row = line.split(",");
            if (valid(row[1], format)) {
                CryptoData cryptoData = new CryptoData(Long.valueOf(row[0].split("\\.")[0]), format.parse(row[1]), row[2],
                        new BigDecimal(row[3]), new BigDecimal(row[4]), new BigDecimal(row[5]),
                        new BigDecimal(row[6]), new BigDecimal(row[7]), new BigDecimal(row[8]));
                cryptoDataRepository.save(cryptoData);
            }
        }
    }

    @GetMapping("/read")
    public void read() {
        ExecutorService service = Executors.newFixedThreadPool(5);
        service.submit(new DataReader());
    }

    private boolean valid(String s, SimpleDateFormat format) {
        try {
            format.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    class DataReader implements Runnable {
        AtomicLong id = new AtomicLong(0);
        Long id1 = 1L;
        @Override
        public void run() {
            while (true) {
                Optional<CryptoData> data = cryptoDataRepository.findById(id1++);
                if (data.isPresent()) {
//                    id.incrementAndGet();
                    System.out.println(data);
                }
            }
        }
    }

    @GetMapping("/candles")
    public List<CandleResponse> getCandleResponse(@RequestBody CandleRequest candleRequest) {
        return coinbaseExchangeService.getCandleResponse(candleRequest);
    }
}
