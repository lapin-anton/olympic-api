import React from 'react';
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import CardMedia from '@mui/material/CardMedia';
import Typography from '@mui/material/Typography';

export default function SportCard(props) {

    const {img, name} = props;

    return (
        <Card>
            <CardMedia
                component="img"
                alt={name}
                height="250"
                image={img}
            />
            <CardContent>
                <Typography gutterBottom variant="h5" component="div">
                    {name}
                </Typography>
            </CardContent>
        </Card>
    );
}