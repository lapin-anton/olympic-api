import React from 'react';
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import CardMedia from '@mui/material/CardMedia';
import Typography from '@mui/material/Typography';
import {CardActionArea} from "@mui/material";

import { withStyles } from '@mui/styles';

import {styles} from "../../css-common";

function SportCard(props) {

    const {img, name, classes} = props;

    return (
        <Card>
            <CardActionArea>
                <CardMedia
                    component="img"
                    alt={name}
                    sx={{width:'inherit'}}
                    image={img}
                />
                <CardContent className={classes.cardContent}>
                    <Typography gutterBottom variant="h5" component="div">
                        {name}
                    </Typography>
                </CardContent>
            </CardActionArea>
        </Card>
    );
}

export default withStyles(styles)(SportCard);