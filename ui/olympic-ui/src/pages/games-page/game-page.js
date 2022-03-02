import React, {Component} from 'react';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableRow from '@mui/material/TableRow';
import {Container, Grid, Paper, TableHead} from "@mui/material";
import {withStyles} from '@mui/styles';
import {styles} from "../../css-common";

import GameService from '../../services/game-service';
import ResultsService from "../../services/results-service";


class GamePage extends Component {
    constructor(props) {
        super(props);
        this.state = {
            game: null,
            rating: [],
            athleteCount: 0
        }
    }

    componentDidMount() {
        this.getGame(this.props.match.params.id);
    }

    getGame = (id) => {
        GameService.getGameById(id)
            .then(response => {
                this.setState({game: response.data});
            })
            .catch(e => console.log(e))
        ResultsService.getCountryTeamRatingByGame(id)
            .then(response => {
                this.setState({rating: response.data})})
            .catch(e => console.log(e));
        GameService.getAthletesCountByGameId(id)
            .then(response => {
                this.setState({athleteCount: response.data.athleteCount});
            })
            .catch(e => console.log(e));
    }

    render() {

        const {game, rating, athleteCount} = this.state;

        const {classes} = this.props;

        return (
            <Container maxWidth="lg">
                <h1 style={{"text-transform":"uppercase", "text-align":"center", "margin-top": "80px"}}>Game Info</h1>
                <Grid container spacing={2}>
                    <Grid item xs={4}>
                        <div>
                            <img
                                src={game && game.thumbnailUrl}
                                alt={game && game.city}
                            />
                        </div>
                    </Grid>
                    <Grid item xs={8}>
                            <TableContainer component={Paper}>
                                <Table aria-label="simple table">
                                    <TableBody>
                                        <TableRow
                                            key={"city"}
                                            sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
                                        >
                                            <TableCell component="th" scope="row">City</TableCell>
                                            <TableCell align="center">{game && game.city}</TableCell>
                                        </TableRow>
                                        <TableRow
                                            key={"year"}
                                            sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
                                        >
                                            <TableCell component="th" scope="row">Year</TableCell>
                                            <TableCell align="center">{game && game.year}</TableCell>
                                        </TableRow>
                                        <TableRow
                                            key={"type"}
                                            sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
                                        >
                                            <TableCell component="th" scope="row">Season</TableCell>
                                            <TableCell align="center">{game && game.type}</TableCell>
                                        </TableRow>
                                        <TableRow
                                            key={"type"}
                                            sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
                                        >
                                            <TableCell component="th" scope="row">Country's Teams</TableCell>
                                            <TableCell align="center">{rating && rating.length}</TableCell>
                                        </TableRow>
                                        <TableRow
                                            key={"type"}
                                            sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
                                        >
                                            <TableCell component="th" scope="row">Athletes</TableCell>
                                            <TableCell align="center">{athleteCount}</TableCell>
                                        </TableRow>
                                    </TableBody>
                                </Table>
                            </TableContainer>
                    </Grid>
                </Grid>
                <Grid container spacing={1}>
                    <Grid item xs={12}>
                        <h2>Unofficial Team Medal Count</h2>
                        <TableContainer component={Paper}>
                            <Table aria-label="simple table">
                                <TableHead>
                                    <TableRow>
                                        <TableCell>Place</TableCell>
                                        <TableCell>Country's Team</TableCell>
                                        <TableCell>Gold</TableCell>
                                        <TableCell>Silver</TableCell>
                                        <TableCell>Bronze</TableCell>
                                        <TableCell>Total</TableCell>
                                    </TableRow>
                                </TableHead>
                                <TableBody>
                                    {rating && rating.map((r, i) => {

                                        let rowStyle = classes.commonRowColor;

                                        if (i === 0) {
                                            rowStyle = classes.goldColor;
                                        }
                                        if (i === 1) {
                                            rowStyle = classes.silverColor;
                                        }
                                        if (i === 2) {
                                            rowStyle = classes.bronzeColor;
                                        }

                                        return (<TableRow className={rowStyle}>
                                            <TableCell>{i + 1}</TableCell>
                                            <TableCell>{r.country}</TableCell>
                                            <TableCell>{r.gold}</TableCell>
                                            <TableCell>{r.silver}</TableCell>
                                            <TableCell>{r.bronze}</TableCell>
                                            <TableCell>{r.total}</TableCell>
                                        </TableRow>);
                                    })}
                                </TableBody>
                            </Table>
                        </TableContainer>
                    </Grid>
                </Grid>
            </Container>
        );
    }
}

export default withStyles(styles)(GamePage);