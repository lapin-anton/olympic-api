create view game_athlete_count_vw as select
    r.game_id as game_id, count(distinct a.id) as athletes from result r
    join athlete a on a.id = r.athlete_id group by r.game_id;

create view game_sport_count_vw as select
    r.game_id as game_id, count(distinct s.id) as sports from result r
    join sport s on r.sport_id = s.id group by r.game_id;

create view athletes_per_game_vw as select a.id id,
                                           a.name a_name, a.surname a_surname, a.gender a_gender, a.url url, r.athlete_age a_age, r.athlete_rank team_rank,
                                           c.name country,
                                           s.name sport,
                                           g.id game_id,
                                           r.gold gold, r.silver silver, r.bronze bronze, r.total total
                                    from athlete a
                                             join result r on a.id = r.athlete_id
                                             join game g on g.id = r.game_id
                                             join sport s on r.sport_id = s.id
                                             join country c on a.country_id = c.id;
