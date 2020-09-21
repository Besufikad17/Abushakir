# Abushakir (ባሕረ ሃሳብ)

The words Bahire Hasab originate from the ancient language of Ge'ez, ( Arabic: Abu Shakir) is a
time-tracking method, devised by the 12th pope of Alexandria, Pope St. Dimitri.

## What does it mean?

"Bahire Hasab /'bəhrɛ həsəb'/  " simply means "An age with a descriptive and chronological number". In some books it can also be found as "Hasabe Bahir", in a sense giving time an analogy, resembling a sea.

This package allows developers to implement Ethiopian Calendar and Datetime System in their application(s)`.

This package is implemented using the [UNIX EPOCH](https://en.wikipedia.org/wiki/Unix_time) which
means it's not a conversion of any other calendar system into Ethiopian, for instance, Gregorian Calendar.

Unix Epoch is measured using milliseconds since 01 Jan, 1970 UTC. In unix epoch leap seconds are ignored.

## Prerequisites

Before you begin, ensure you have met the following requirements:

* ```json-simple-3.0.2.jar```


## Getting started



In your library add the following import:

```Java
import abushakir.*;
```
## Example

```java
           
          /**
          * Ethiopian Datetime Module [etDateTime]
          */
           
           etDateTime now = new etDateTime();
           now = now.now();
           System.out.println(now.getDate(now.getYear(),now.getMonth(), now.getDay()));
           System.out.println(now.getTime(now.getHour(),now.getMinute(),now.getSecond()));
   
           etDateTime covidFirstConfirmed = new etDateTime(2012,  7,  4);
           etDateTime covidFirstConfirmedEpoch = covidFirstConfirmed.fromMillisecondsSinceEpoch(covidFirstConfirmed.moment);
   
           etDateTime covidFirstDeath = etDateTime.Parse("2012-07-26 23:00:00");
           System.out.println(covidFirstDeath.to_String());
   
           /// Comparison of two EtDatetime Instances
   
           Duration daysWithOutDeath = covidFirstConfirmed.differenceInDays(covidFirstDeath);
           System.out.println(daysWithOutDeath);  //22*24*360000
   
   
           System.out.println(covidFirstDeath.isAfter(covidFirstConfirmed));
   
           System.out.println(covidFirstDeath.isBefore(now));
   
           System.out.println(covidFirstConfirmed.isAtSameMomentAs(covidFirstConfirmedEpoch));
   
           /**
            * Ethiopian Calendar Module [ETC]
            */
   
           ETC ethiopianCalendar = new ETC( 2011,  13,  4);
   
           try {
               System.out.println(Arrays.toString(ethiopianCalendar.monthDays(true, true)));    // Iterable Object of the given month
               System.out.println(Arrays.toString(ethiopianCalendar.monthDays()));        // => [2012, 7, 1, 1]
           } catch (
                   Calander_Exceptions.MonthNumberException e) {
               e.printStackTrace();
           }
           System.out.println(ethiopianCalendar.nextMonth().getMonth()); // => ETC instance of nextMonth, same year
           System.out.println(ethiopianCalendar.previousYear().getYear()); // => ETC instance of prevYear, same month
   
   
           /**
            * Bahire Hasab Module [BahireHasab]
            */
   
   
           Bahirehasab bh2 = new Bahirehasab(); // Get's the current year
           Bahirehasab bh = new Bahirehasab(2012);
           try {
               System.out.println(bh.getSingleBealOrTsom("ትንሳኤ").toString()); // {month: ሚያዝያ, date: 16}
           } catch (Calander_Exceptions.BealNameException e) {
               e.printStackTrace();
           }
   
           System.out.println(bh.getAllAtswamat("ትንሳኤ",69).toString()); // => List of All fasting and Movable holidays
           System.out.println(bh.getEvange(true)); // => ሉቃስ
```
For further implementation example see [this JavaFx application](https://github.com/Besufikad17/CalanderFx)


## Contributors

Thanks to the following people who have contributed to this project:

* [@Besufikad](https://github.com/Besufikad17) 📖
* [@Yohannes](https://github.com/YohannesTz) 📖

<!---You might want to consider using something like the [All Contributors](https://github.com/all-contributors/all-contributors) specification and its [emoji key](https://allcontributors.org/docs/en/emoji-key).--->

## Contact

If you want to contact me you can reach me at <besumicheal@gmail.com>.

## License
<!--- If you're not sure which open license to use see https://choosealicense.com/--->

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE) file for details
