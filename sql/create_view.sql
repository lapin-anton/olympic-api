create view game_athlete_count_vw as select
    r.game_id as game_id, count(distinct a.id) as athletes from result r
    join athlete a on a.id = r.athlete_id group by r.game_id;

create view game_sport_count_vw as select
    r.game_id as game_id, count(distinct s.id) as sports from result r
    join sport s on r.sport_id = s.id group by r.game_id;
