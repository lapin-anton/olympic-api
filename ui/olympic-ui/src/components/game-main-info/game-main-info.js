import React from 'react';

import {withStyles} from '@mui/styles';
import {styles} from "../../css-common";
import {Paper} from "@mui/material";
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableRow from "@mui/material/TableRow";
import TableCell from "@mui/material/TableCell";
import TableContainer from "@mui/material/TableContainer";

function GameMainInfo(props) {

    const {classes, game, teamCount, athleteCount, sportCount} = props;

    return (
        <TableContainer component={Paper}>
            <Table aria-label="simple table">
                <TableBody>
                    <TableRow
                        key={"city"}
                        sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
                        className={classes.oddRow}
                    >
                        <TableCell component="th" scope="row">City</TableCell>
                        <TableCell align="center">{game && game.city}</TableCell>
                    </TableRow>
                    <TableRow
                        key={"year"}
                        sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
                        className={classes.evenRow}
                    >
                        <TableCell component="th" scope="row">Year</TableCell>
                        <TableCell align="center">{game && game.year}</TableCell>
                    </TableRow>
                    <TableRow
                        key={"type"}
                        sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
                        className={classes.oddRow}
                    >
                        <TableCell component="th" scope="row">Season</TableCell>
                        <TableCell align="center">{game && game.type}</TableCell>
                    </TableRow>
                    <TableRow
                        key={"type"}
                        sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
                        className={classes.evenRow}
                    >
                        <TableCell component="th" scope="row">Country's Teams</TableCell>
                        <TableCell align="center">{teamCount}</TableCell>
                    </TableRow>
                    <TableRow
                        key={"type"}
                        sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
                        className={classes.oddRow}
                    >
                        <TableCell component="th" scope="row">Athletes</TableCell>
                        <TableCell align="center">{athleteCount}</TableCell>
                    </TableRow>
                    <TableRow
                        key={"type"}
                        sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
                        className={classes.evenRow}
                    >
                        <TableCell component="th" scope="row">Sports</TableCell>
                        <TableCell align="center">{sportCount}</TableCell>
                    </TableRow>
                </TableBody>
            </Table>
        </TableContainer>
    );

}

export default withStyles(styles)(GameMainInfo);