import React from 'react';

import {withStyles} from '@mui/styles';
import {styles} from "../../css-common";
import {
    Accordion,
    AccordionDetails,
    AccordionSummary, Avatar,
    Chip, Divider,
    List,
    ListItem,
    Paper,
    Stack,
    Typography
} from "@mui/material";
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableRow from "@mui/material/TableRow";
import TableCell from "@mui/material/TableCell";
import TableContainer from "@mui/material/TableContainer";

function AthleteMainInfo(props) {

    const {data, onSelectGame, classes} = props;

    return (
        <TableContainer component={Paper}>
            <Table aria-label="simple table">
                <TableBody>
                    <TableRow
                        key={"city"}
                        sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
                        className={classes.oddRow}
                    >
                        <TableCell component="th" scope="row">Gender</TableCell>
                        <TableCell align="center">{data && data.gender}</TableCell>
                    </TableRow>
                    <TableRow
                        key={"year"}
                        sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
                        className={classes.evenRow}
                    >
                        <TableCell component="th" scope="row">Country</TableCell>
                        <TableCell align="center">{data && data.country.name}</TableCell>
                    </TableRow>
                </TableBody>
            </Table>
            <Accordion>
                <AccordionSummary>
                    <h2 className={classes.listHead}>Results</h2>
                </AccordionSummary>
                <AccordionDetails>
                    <List>
                        {data && data.results.map(r => {
                            return(
                                <ListItem divider>
                                    <Avatar
                                        alt={r.game.city}
                                        src={r.game.thumbnailUrl}
                                        sx={{ width: 50, height: 50 }}
                                        style={{marginRight:'7px'}}
                                        onClick={() => onSelectGame(r.game.id)}
                                    />
                                    <Typography>{r.game.city} {r.game.year} {r.game.type} / {r.sport.name} / Age: {r.athleteAge} /
                                        <Stack direction="row" spacing={1}>
                                            <Chip style={{"backgroundColor":"#ffd700"}} label={r.gold}/>
                                            <Chip style={{"backgroundColor":"#c0c0c0"}} label={r.silver}/>
                                            <Chip style={{"backgroundColor":"#cd7f32"}} label={r.bronze}/>
                                        </Stack>
                                    </Typography>
                                </ListItem>
                            );
                        })}
                    </List>
                </AccordionDetails>
            </Accordion>
        </TableContainer>
    );

}

export default withStyles(styles)(AthleteMainInfo);